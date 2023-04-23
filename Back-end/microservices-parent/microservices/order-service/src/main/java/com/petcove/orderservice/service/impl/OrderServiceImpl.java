package com.petcove.orderservice.service.impl;

import com.petcove.orderservice.dto.InventoryResponse;
import com.petcove.orderservice.dto.OrderLineItemsDto;
import com.petcove.orderservice.dto.OrderRequest;
import com.petcove.orderservice.event.OrderPlacedEvent;
import com.petcove.orderservice.model.Order;
import com.petcove.orderservice.model.OrderLineItems;
import com.petcove.orderservice.model.OrderStatus;
import com.petcove.orderservice.model.adapter.OrderAdapter;
import com.petcove.orderservice.repository.OrderRepository;
import com.petcove.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest){
        /*
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        log.info(order.getOrderNumber());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::DtoToOrderItems)
                .toList();
        log.info("itemlist");
        order.setOrderLineItemsList(orderLineItems);

        order.setCustomerId(orderRequest.getCustomerId());
        log.info("customerid");
        order.setTotalAmount(orderRequest.getTotalAmount());
        log.info("amount");
        order.setOrderStatus(OrderStatus.Handling);
        log.info("status");
        */
        Order order = OrderAdapter.toOrderEntity(orderRequest);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // Call Inventory service and place order if the product is in stock;
        InventoryResponse[] inventoryResponseArr = webClientBuilder.build().get() //synchronous request
                //build the uri with the query params as skuCodes
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class) //read the response
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponseArr)
                .allMatch(InventoryResponse::isInStock);
        log.info(String.valueOf(allProductsInStock));

        if(allProductsInStock){
            order.setOrderStatus(OrderStatus.PLACED);
            orderRepository.save(order);
            // send the order placed event as a msg to the notification topic
            //kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            kafkaTemplate.send("orderplacedEvent", new OrderPlacedEvent(order.getId()
                    , order.getCustomerId()
                    , order.getTotalAmount()
                    , OrderAdapter.toOrderItemEventList(order.getOrderLineItemsList())));
            //log.debug();
            return "Order placed successfully.";
        }else {
            throw new IllegalArgumentException("Product is not in stock at the moment.");
        }


    }
    public OrderLineItems DtoToOrderItems(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}
