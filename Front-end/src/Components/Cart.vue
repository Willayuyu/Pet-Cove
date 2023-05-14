<template>
  <div>
    <div :class="cClass">
      <div class="cart-menu">
        <h1 class="text-center">Cart</h1>
        <hr />

        <transition name="fade">
          <div v-if="cartContent.length === 0" class="text-center font-italic">
            Your cart is empty, try adding some stuff.
          </div>
        </transition>

        <transition-group name="fade">
          <div class="row" v-for="item in cartContent" :key="item.productId">
            <div class="col-4">
              <img :src="item.productImage" style="width: 90px" />
            </div>
            <div class="col-6">
              <h4>{{ item.productName }}</h4>
              <h6>{{ item.productPrice }}</h6>
              <div class="btn-group" role="group">
                <button
                  class="btn btn-secondary"
                  @click="decreaseQuantity(item)"
                >
                  -
                </button>
                <button class="btn btn-light">
                  {{ item.productQuantity }}
                </button>
                <button
                  class="btn btn-secondary"
                  @click="increaseQuantity(item)"
                >
                  +
                </button>
              </div>
            </div>
            <div class="col-2 pt-4">
              <span class="remove-btn" @click="removeItem(item.productId)"
                >remove</span
              >
            </div>
          </div>
        </transition-group>

        <hr v-if="cartContent.length !== 0" />
        <div
          class="row justify-content-between"
          style="background: #7dcf85; padding: 10px 10px 10px 10px"
          v-if="cartPrice != undefined"
        >
          <div class="flex-column pl-3">
            <h4>Total</h4>
          </div>
          <div class="flex-column pr-3">
            <h4>${{ cartPrice }}</h4>
          </div>
        </div>
        <button class="btn btn-primary btn-checkout" @click="checkout">
          Checkout
        </button>
      </div>
    </div>

    <div :class="modalClass" @click="cartON"></div>
  </div>
</template>

<script>
/* eslint-disable */
import axios from "axios";

export default {
  data() {
    return {
      cartContent: [],
      cartPrice: 0,
      cClass: "cart",
      modalClass: "model off",
      cartId: '',
      userId: '',
      userName:'',
      productId:''
      
    };
  },
  mounted() {
    this.fetchCartContent();
  },
  methods: {
    fetchCartContent() {
      axios
        .get(`/api/cart/getAllCartItems?userId=${this.$store.state.userId}`)
        .then((response) => {
          this.cartContent = response.data;
          this.cartId = response.data.cartId;
          this.userId = response.data.userId;
          this.userName = response.data.userName;
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
          this.cartPrice = response.data.totalPrice.toFixed(2);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    removeItem(productId) {
      axios
        .delete(
          `/api/cart/deleteCartItem?cartId=${this.cartId}&userId=${this.userId}&productId=${productId}`
        )
        .then((response) => {
          if (response.data === "Item removed from cart successfully.") {
            this.fetchCartContent();
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    increaseQuantity(item) {
      item.productQuantity++;
      this.updateCartItem(item.productQuantity);
    },
    decreaseQuantity(item) {
      if (item.productQuantity > 1) {
        item.productQuantity--;
        this.updateCartItem(item.productQuantity);
      }
    },
    updateCartItem(item) {
      axios
        .put(`/api/cart/updateCartItem?cartId=${this.cartId}`, item)
        .then((response) => {
          this.calculateCartPrice();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    checkout() {
      // make an API call to create an order using the cart items
      axios
        .post("/api/order/createOrder", {
          userId: this.userId,
          cartId: this.cartId,
          items: this.cartContent,
          total: this.cartPrice,
        })
        .then((response) => {
          // redirect to the order confirmation page
          this.$router.push({ path: `/order/${response.data.orderId}` });
        })
        .catch((error) => {
          console.error(error);
        });
    },
    cartON() {
      if (this.cClass === "cart on") {
        this.cClass = "cart";
        this.modalClass = "modal off";
      } else {
        this.cClass = "cart on";
        this.modalClass = "modal";
      }
    },
  },
};
</script>


<style scoped>
/* Modal Overlay */
.modal {
  display: block;
  z-index: 1050;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}
.modal.off {
  display: none;
}
/* Transitions */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
/* Cart Body */
.cart {
  position: fixed;
  margin-right: 0px;
  top: 0;
  right: 0;
  width: 360px;
  height: 100%;
  background: #303e49;
  overflow-y: auto;
  z-index: 1051;
  -webkit-overflow-scrolling: touch;
  transform: translateX(360px);
  transition-property: transform;
  transition-duration: 0.4s;
}
.cart.on {
  transform: translateX(0);
  -webkit-overflow-scrolling: touch;
  transition-property: transform;
  transition-duration: 0.4s;
}

.cart-menu {
  color: #eee;
  margin-left: 10px;
  margin-right: 15px;
}

hr {
  border-color: white;
}

.row {
  margin-top: 10px;
  margin-bottom: 10px;
}

.remove-btn {
  border-radius: 50%;
  content: url("../assets/multiply.png");
}

.remove-btn:hover {
  background-color: grey;
}
.cart-button-container {
}
.checkout-btn {
}
.btn-checkout {
  display: block;
  margin: 20px auto 0;
  width: 100%;
  max-width: 360px;
}
</style>
