package com.example.springboot.controller;

import com.example.springboot.entity.Product;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * On the product page, this api can select all the product information and dispaly on the screen.
     * @return list
     */
    @GetMapping("/findAllProduct")
    public List<Product> findAllProduct(){
        List<Product> list = productService.findAllProduct();
        return list;
    }

    /**
     * Sellers can check all the products they have listed on the product management page.
     * @param sellerId
     * @return list1
     */
    @GetMapping("/findAllProductForSeller")
    public List<Product> findAllProductForSeller(@RequestParam(value = "sellerId", required = false) Integer sellerId){
        List<Product> list1 = productService.findAllProductForSeller(sellerId);
        return list1;
    }

    /**
     * On the product page, user can select one specific product and check the detailed information of the product.
     * @param productId
     * @return list2
     */
    @GetMapping("/findProductDetails")
    public List<Product> findProductDetails(@RequestParam(value = "productId", required = false) Integer productId){
        List<Product> list2 = productService.findProductDetails(productId);
        return list2;
    }


    /**
     * On the product page, user can select product through the input for example, the productName, productCategories,productColor,price range.
     * @param productName
     * @param productCategories
     * @param productColor
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @GetMapping("/findProductByInput")
    public List<Product> findProductByInput(@RequestParam(value = "productName", required = false) String productName,
                                            @RequestParam(value = "productCategories", required = false) String productCategories,
                                            @RequestParam(value = "productColor", required = false) String productColor,
                                            @RequestParam(value = "minPrice", required = false) Float minPrice,
                                            @RequestParam(value = "maxPrice", required = false) Float maxPrice){
        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);
        params.put("productCategories", productCategories);
        params.put("productColor", productColor);
        params.put("minPrice",minPrice);
        params.put("maxPrice",maxPrice);


        return productService.findProductByInput(params);
    }

    /**
     * Seller can add the new product to sell on the product manage page.
     * @param product
     * @return
     */
    @PostMapping("/insertProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.insertProduct(product);
        return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
    }

    /**
     * Seller can delete product on the product manage page.
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}/deleteProductById")
    public String deleteProductById(@PathVariable("productId") Integer productId) {
        int rowsAffected = productService.deleteProductById(productId);
        if (rowsAffected > 0) {
            return "Product deletes successfully!";
        } else {
            return "Failed to delete product!";
        }
    }

    /**
     * Seller can update product information on the product manage page.
     * @param productId
     * @param product
     * @return
     */
    @PutMapping("/{productId}/updateProductById")
    public ResponseEntity<String> updateProductById(@PathVariable("productId") Integer productId, @RequestBody Product product) {
        product.setProductId(productId);
        int updatedRows = productService.updateProductById(product);

        if (updatedRows > 0) {
            return ResponseEntity.ok().body("Product updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }



}
