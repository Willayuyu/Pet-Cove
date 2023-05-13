<template>
  <div>
    <div class="row mb-5">
      <div class="col6 col-xl-6 col-lg-6 col-md-12 col-sm-12">
        <img class="img-fluid" :src="product.productImage" />
      </div>

      <div
        class="col6 col-xl-6 col-lg-6 col-md-12 col-sm-12 d-flex align-items-center justify-content-start"
      >
        <div class="info pt-xl-0 pt-lg-0 pt-5">
          <span class="float-left pr-3">★★★★★</span>
          <h6 style="width: 190px">3 reviews</h6>
          <h1 class="font-weight-bold text-uppercase pt-3">
            {{ product.productName }}
          </h1>
          <h4>${{ product.productPrice }}</h4>
          <br /><br /><br />
          <div class="control number text-center">
            <button
              class="decrement-button"
              @click="dec"
              style="
                border-right: 0.2px solid lightgrey;
                float: left;
                margin-right: 11px;
              "
            >
              −
            </button>
            <span>{{ quan }}</span>
            <button
              class="increment-button"
              @click="inc"
              style="border-left: 0.2px solid lightgrey; margin-left: 16px"
            >
              +
            </button>
            <br /><br />
          </div>
          <button class="add-to-cart-button" @click="addToCart(product)">
            ADD TO CART
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import axios from "axios";
export default {
  props: ["information"],
  name: "InfoBox",
  data() {
    return {
      quan: 1,
    };
  },
  methods: {
    inc() {
      // Info box Incrememnt button
      if (this.quan <= 8) return this.quan++;
    },
    dec() {
      // Info box Decrememnt button
      if (this.quan >= 2) return this.quan--;
    },
    addtoCart(item) {
      // Info box Add to cart button
      const requestData = {
        userId: item.userId,
        userName: item.userName,
        productId: item.productId,
        productName: item.productName,
        productPrice: item.productPrice,
        productQuantity: this.quan,
        productImage: item.productImage,
      };
      axios
        .post("/api/cart/addProductToCart", requestData)
        .then((response) => {
          console.log(response.data); // should log "Product added successfully"
          // add some code to update the cart count in the UI
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.img-fluid {
  min-width: 90% !important;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.control.number {
  border: 0.2px solid lightgrey;
  font-size: 19px;
  font-weight: bold;
  height: 35px;
  width: 155px;
  margin-bottom: 30px;
}
.control.number button {
  border: none;
  background: inherit;
  width: 56px;
  height: 35px;
  outline-style: none;
}
.control.number button:active {
  background-color: lightgrey;
}
.control.number h5 {
  margin-left: 13px;
  margin-right: 13px;
}
.add-to-cart-button {
  -webkit-transition-duration: 500ms;
  transition-duration: 500ms;
  width: 155px;
  height: 70px;
  background-color: #2c3539;
  color: #fff;
  font-size: 15px;
  padding: 0px 30px;
  cursor: pointer;
  position: relative;
  top: 0;
  z-index: 10;
  border: none;
  box-shadow: 0 26px 38px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
.add-to-cart-button:hover,
.add-to-cart-button:focus {
  background-color: inherit;
  color: black;
}
</style>
