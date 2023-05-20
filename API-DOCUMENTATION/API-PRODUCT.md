## API-PRODUCT

## find all product（Get)

```
/api/product/findAllProduct
```

**Description:** On the product page, this api can select all the product information and dispaly on the screen.

**Front-end data transfer**：null

**Back-end data transfer:**

```
Integer productId; Unique Identifier
String productName; 
String productDescription;
Float productPrice;
Integer productNum;
String productImage; Product image path
String productCategories; 
String productColor;
Integer sellerId;
```

## findAllProductForSeller（Get）

```
/api/product/findAllProductForSeller
```

**Description:** Sellers can check all the products they have listed on the product management page.

**Front-end data transfer：**

```
Integer sellerId
```

**Back-end data transfer:**

```
Integer productId; Unique Identifier
String productName; 
String productDescription;
Float productPrice;
Integer productNum;
String productImage; Product image path
String productCategories; 
String productColor;
Integer sellerId;
```

## findProductDetails(Get)

```
/api/product/findProductDetails
```

**Description:** On the product page, user can select one specific product and check the detailed information of the product.

**Front-end data transfer：**

```
Integer productId
```

**Back-end data transfer:**

```
Integer productId; Unique Identifier
String productName; 
String productDescription;
Float productPrice;
Integer productNum;
String productImage; Product image path
String productCategories; 
String productColor;
Integer sellerId;
```

## findProductByInput(Get)

```
/api/product/findProductByInput
```

**Description:** On the product page, user can select product through the input for example, the productName, productCategories,productColor,price range.

**Front-end data transfer：**

```
 String productName,
 String productCategories,
 String productColor,
 Float minPrice,
 Float maxPrice
```

**Back-end data transfer:**

```
Integer productId; Unique Identifier
String productName; 
String productDescription;
Float productPrice;
Integer productNum;
String productImage; Product image path
String productCategories; 
String productColor;
Integer sellerId;
```

## insertProduct(Post)

```
/api/product/insertProduct
```

**Description:** Seller can add the new product to sell on the product manage page.

**Front-end data transfer：**

```
String productName; 
String productDescription;
Float productPrice;
Integer productNum;
String productImage; Product image path
String productCategories; 
String productColor;
Integer sellerId;
```

**Back-end data transfer:**

```
Product added successfully.
```

## deleteProductById(Delete)

```
/api/product/{productId}/deleteProductById
```

**Description:** Seller can delete product on the product manage page.

**Front-end data transfer：**

```
Integer productId
```

**Back-end data transfer:**

```
Product deletes successfully!
or
Failed to delete product!
```

## updateProductById（Put)

```
/api/product/{productId}/updateProductById
```

**Description:** Seller can update product information on the product manage page.

**Front-end data transfer：**

```
Integer productId; Unique Identifier
String productName; 
String productDescription;
Float productPrice;
Integer productNum;
String productImage; Product image path
String productCategories; 
String productColor;
Integer sellerId;
```

**Back-end data transfer:**

```
Product updated successfully.
or 
Product not found.
```

