<template>
  <div>
    <transition-group name="fade" class="row" tag="div">
      <div
        v-for="item in CardArray"
        class="col-6 col-xl-4 col-lg-4 col-md-4 col-sm-6 col-xs-4 pb-3"
        :key="item.productId"
      >
        <div class="card">
          <img
            class="card-img-top"
            :src="item.productImage"
            alt="Card image cap"
          />
          <div class="overlay">
            <button
              type="button"
              class="btn btn-outline-secondary btn-lg"
              @click="addToCart(item)"
            >
              Add +
            </button>
            <router-link
              :to="{ name: 'Info', params: { productId: item.productId } }"
              ><button type="button" class="btn btn-outline-secondary btn-lg">
                Info
              </button></router-link
            >
          </div>
          <div class="card-body">
            <h5 class="card-title">{{ item.productName }}</h5>
            <p class="card-text">${{ item.productPrice }}</p>
          </div>
        </div>
      </div>
    </transition-group>
  </div>
</template>

<script>
/* eslint-disable */
import axios from "axios";
import Cart from "@/Components/Cart.vue";
export default {
  props: {
    CardArray: {
      type: Array,
      required: true,
    },
  },
  methods: {
    getAllProduct() {
      axios
        .get("/api/product/findAllProduct")
        .then((response) => {
          this.CardArray = response.data;
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addToCart(item) {
      // make a post request to add the product to the cart
      const requestData = {
        userId: this.$store.state.userId, 
        userName: this.$store.state.username, 
        productId: item.productId,
        productName: item.productName,
        productPrice: item.productPrice,
        productQuantity: 1,
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
        this.$refs.cartMove.cartON();
    },
  },
};
</script> 

<style>
/* transition Group style */
.fade-move {
  transition: transform 1s;
}
/* Card Style */
.card {
  transition: 500ms;
  position: relative;
  overflow: hidden;
}

.card img {
  z-index: 1;
}

.card button {
  width: 140px;
  margin-bottom: 10px;
}

.card:hover img {
  filter: blur(4px);
}

.card:hover .overlay {
  opacity: 0.8;
}

.card .overlay {
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 70%;
  background-color: #232b34;
  opacity: 0;
  z-index: 100;
  transition: all 0.3s ease-in;
}

.card:hover,
.card:active {
  transform: scaleY(1.1) scaleX(1.06);
  box-shadow: 0 14px 98px rgba(0, 0, 0, 0.25), 0 0px 60px rgba(0, 0, 0, 0.22);
}
</style>
