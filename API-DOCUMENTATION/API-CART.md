# API-CART

## addProductToCart（Post)

```
/api/cart/addProductToCart
```

**Description:**Users can add the product to their cart.

**Front-end data transfer**：

```
Integer userId;
String userName;
Integer productId;
String productName;
Long productPrice;
Integer productQuantity;
String productImage;
```

**Back-end data transfer:**

```
Product added successfully.
```

## deleteCartItem(Delete)

```
/api/cart/deleteCartItem
```

**Description:**Users can delete the product in their cart.

**Front-end data transfer**：

```
Integer userId,
Integer productId
```

**Back-end data transfer:**

```
Item removed from cart successfully.
or
Failed to remove item from cart.
```

## updateCartItemQuantity(Put)

```
/api/cart/updateCartItemQuantity
```

**Description:**Users can update the quantity of product in their cart.

**Front-end data transfer**：

```
Integer userId,
Integer productId,
Integer productQuantity
```

**Back-end data transfer:**

```
Item quantity updated successfully.
or
Failed to update item quantity.
```

## calculateTotalPrice(Get)

```
/api/cart/totalPrice
```

**Description:**It can help the user to calculate the total price of the products in their cart.

**Front-end data transfer**：

```
Integer userId
```

**Back-end data transfer:**

```
Long totalPrice
```

## getAllCartItems(Get)

```
/api/cart/getAllCartItems
```

**Description:**It can help the user to check all  products in their cart.

**Front-end data transfer**：

```
Integer userId
```

**Back-end data transfer:**

```
Integer cartId;
Integer userId;
String userName;
Integer productId;
String productName;
Long productPrice;
Integer productQuantity;
String productImage;
```

