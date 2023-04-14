<template>
    <div class="page-container">
        <h2>Checkout</h2>
        <div v-if="!isLoggedIn">
            <p>Please log in or checkout as a guest:</p>
            <button @click="login">Log In</button>
            <button @click="checkoutAsGuest">Checkout as Guest</button>
        </div>
        <div v-else>
            <p>Logged in as {{ username }}</p>
            <h3>Order Summary:</h3>
            <ul>
                <li v-for="(item, index) in items" :key="index">
                    {{ item.name }} - ${{ item.price }}
                </li>
            </ul>
            <p>Total: ${{ totalPrice }}</p>
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

export default {
    data() {
        return {
            isLoggedIn: false,
            username: "",
            items: [
                { name: "Item 1", price: 10 },
                { name: "Item 2", price: 20 },
                { name: "Item 3", price: 30 },
            ],
            selectedPaymentMethod: "credit-card",
            cardNumber: "",
            expiryDate: "",
            cvv: "",
            paypalEmail: "",
        };
    },
    computed: {
        totalPrice() {
            return this.items.reduce((total, item) => total + item.price, 0);
        },
    },
    methods: {
        login() {
            // implement login functionality here
            // set isLoggedIn to true and set username
        },
        checkoutAsGuest() {
            // implement checkout as guest functionality here
            // set isLoggedIn to false and leave username empty
        },
        processPayment() {
            if (this.selectedPaymentMethod === "credit-card") {
                // implement credit card payment processing here
                /* eslint-disable */
                console.log("Processing credit card payment...");
            } else if (this.selectedPaymentMethod === "paypal") {
                // implement PayPal payment processing here
            }
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