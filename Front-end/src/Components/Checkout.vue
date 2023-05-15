<template>
    <div class="page-container">
        <h2>Checkout</h2>
        <div v-if="!isLoggedIn">
            <p>Please log in or checkout as a guest:</p>
            <button @click="login">Log In</button>
        </div>
        <div v-else>
            <p>Logged in as {{ username }}</p>
            <h3>Order Summary:</h3>
            <ul>
                <li v-for="item in items" :key="item.productId">
                    {{ item.productName }} - {{ item.productQuantity }} * ${{ item.productPrice }}
                </li>
            </ul>
            <p>Total: ${{ cartPrice }}</p>
            <h3>Select Payment Method:</h3>
            <div>
                <input type="radio" id="credit-card" name="payment-method" value="credit-card"
                    v-model="selectedPaymentMethod" />
                <label for="credit-card">Credit Card</label>
            </div>
            <div>
                <input type="radio" id="paypal" name="payment-method" value="paypal" v-model="selectedPaymentMethod" />
                <label for="paypal">PayPal</label>
            </div>
            <div v-if="selectedPaymentMethod === 'credit-card'">
                <h4>Credit Card Details</h4>
                <label for="card-number">Card Number:</label>
                <input type="text" id="card-number" v-model="cardNumber" />
                <label for="expiry-date">Expiry Date:</label>
                <input type="text" id="expiry-date" v-model="expiryDate" />
                <label for="cvv">CVV:</label>
                <input type="text" id="cvv" v-model="cvv" />
            </div>
            <div v-if="selectedPaymentMethod === 'paypal'">
                <h4>PayPal Details</h4>
                <label for="paypal-email">PayPal Email:</label>
                <input type="text" id="paypal-email" v-model="paypalEmail" />
            </div>
            <button @click="processPayment">Process Payment</button>
        </div>
    </div>
</template>
  
<script>
/* eslint-disable */
import { Script } from "vm";
import axios from "axios";

export default {
    data() {
        return {
            isLoggedIn: this.$store.state.isLogin,
            username: this.$store.state.username,
            items: [],
            selectedPaymentMethod: "credit-card",
            cardNumber: "",
            expiryDate: "",
            cvv: "",
            paypalEmail: "",
            cartPrice: 0,
            productIdList: [],
        };
    },
    mounted() {
        this.fetchCartContent();
    },

    // computed: {
    //     totalPrice() {
    //         return this.items.reduce((total, item) => total + item.price, 0);
    //     },
    // },
    methods: {
        fetchCartContent() {
            axios
                .get(`/api/cart/getAllCartItems?userId=${this.$store.state.userId}`)
                .then((response) => {
                    this.items = response.data;
                    this.calculateCartPrice();
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        calculateCartPrice() {
            axios
                .get(`/api/cart/totalPrice?userId=${this.$store.state.userId}`)
                .then((response) => {
                    // console.log(response.data)
                    this.cartPrice = response.data;
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        login() {
            this.$router.push('/login');
        },
        processPayment() {
            // if (this.selectedPaymentMethod === "credit-card") {
            //     // implement credit card payment processing here
            //     /* eslint-disable */
            //     console.log("Processing credit card payment...");
            // } else if (this.selectedPaymentMethod === "paypal") {
            //     // implement PayPal payment processing here
            // }
            // make an API call to create an order using the cart items
            this.productIdList = [];
            for (let i = 0; i < this.items.length; i++) {
                for (let j = 0; j < this.items[i].productQuantity; j++) {
                    this.productIdList.push(this.items[i].productId);
                }
            }
            // console.log(this.productIdList);

            axios
                .post("/api/order/Checkout", {
                    buyerId: this.$store.state.userId,
                    productIds: this.productIdList,
                    shippingAddress: this.$store.state.address,
                })
                .then((response) => {
                    // redirect to the order confirmation page

                    this.$router.push("/buyerHomePage");
                    alert("Payment successfully!")
                    for(let i= 0; i<this.items.length;i++){
                        axios
                            .delete(
                                `/api/cart/deleteCartItem?userId=${this.$store.state.userId}&productId=${this.items[i].productId}`
                            )
                            .then((response) => {
                                if (response.data === "Item removed from cart successfully.") {
                                    this.fetchCartContent();
                                }
                            })
                            .catch((error) => {
                                console.error(error);
                            });
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        },
    },
};
</script>

<style scoped>
/* Button styles */
button {
    background-color: #4caf50;
    /* Green */
    border: none;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}

button:hover {
    background-color: #3e8e41;
}

/* Payment method styles */
input[type="radio"] {
    margin: 10px;
}

/* Credit card input styles */
label {
    display: block;
    margin: 10px 0;
    font-weight: bold;
}

input[type="text"] {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
}

input[type="text"]:focus {
    border-color: #4caf50;
}

/* Order summary styles */
h3 {
    margin-top: 30px;
}

ul {
    list-style: none;
    padding: 0;
}

li {
    margin: 10px 0;
}

p {
    margin: 10px 0;
}

.total-price {
    font-weight: bold;
    font-size: 20px;
    margin-top: 20px;
}

/* Page container styles */
.page-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f2f2f2;
    border-radius: 5px;
    box-shadow: 0px 0px 5px 0px rgba(0, 0, 0, 0.75);
}
</style>