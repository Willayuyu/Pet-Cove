# API-ORDER

## CheckOut (post)

```
/api/order/CheckOut
```

**Description:** Buyer checkout his goods from cart. The front-end will send the information of the products to back-end, then the back-end will generate order and order item related entities. 

**Front-end data transfer:**

```
(Body)
Integer BuyerId; The id of the buyer.
List <Integer> productIds; The id list of the products in this order.
String shippingAddress; The address of the buyer.
```

**Back-end data transfer:**

```
Integer state; 1 means success.
```

## BuyerGetOrderList (get)

```
/api/order/BuyerGetOrderList
```

**Description:** Buyer get all orders he created. Front-end will send a buyer id to back-end,  then back-end will send back all the order details to front-end. 

**Front-end data transfer:**

```
Integer BuyerId; The id of the buyer.
```

**Back-end data transfer:**

```
List <OrderDetails> orderList; The list of the order details.
OrderDetails: {
        Integer id; The id of the order details.
        Integer buyerId;
        Integer sellerId;
        Float cost; The total cost of this order.
        Date createdDate; The date when order is created.
        Time createdTime; The specific time when order is created.
        String status; the status of the order, e.g. delivered, cancelled
        String shippingAddress; 
}
```

## GetOneOrder (get)

```
/api/order/GetOneOrder
```

**Description:** Buyer get details of a specific order, which can see the specific products he bought. The front end will send a order id to back-end which is the unique id of the orderDetails entity. Back-end will send back a json list which including the detail information.

**Front-end data transfer:**

```
Integer orderId; The id of the order details.
```

**Back-end data transfer:**

```
List <JSONObject> itemList; The list of the product items buyer bought.
JSONObject: {
          Float total; The total price.
          Id orderItemId; The id of an specific order item.
          Float price; The single price of the product item.
          String name; The name of the product item.
          Integer count; The count of this product item.
}
```

## BuyerCancelOrder (get)

```
/api/order/BuyerCancelOrder
```

**Description:** Buyer cancel an order before it is delivered. The front end should send an order id, then the back-end will change the state of the order to "Cancelled".

**Front-end data transfer:**

```
Integer orderId; The id of the order details.
```

**Back-end data transfer:**

```
Integer state; 1 means success.
```
