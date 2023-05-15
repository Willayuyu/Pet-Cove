<template>
  <div class="container grid">
    <div class="row justify-content-around">
      <div class="row col-6 pb-4 pr-1">
        <div class="dropdown">
          <a
            class="btn btn-light dropdown-toggle"
            role="button"
            id="dropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
            >SORT BY
            <span style="color: #f2be00">{{ sortButton }}</span>
          </a>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <a class="dropdown-item" @click="sortPriceAsc">Price Ascending</a>
            <a class="dropdown-item" @click="sortPriceDesc">Price Descending</a>
          </div>
        </div>
        <div class="ml-3">
          <input
            type="text"
            placeholder="Search"
            v-model="searchText"
            class="form-control"
            @keyup.enter="search"
          />
        </div>
      </div>
      <div class="row col-6 flex-row-reverse"></div>
      <div class="row justify-content-center">
        <div class="col col-xl-3 col-lg-3 d-none d-lg-block d-xl-block">
          <div class="card-selector">
            <div class="card-body p-5">
              <div class="search-title">
                <h4>Catagories +</h4>
                <br />
                <h6
                  v-for="(category, index) in categories"
                  :key="index"
                  @click="sortI(category)"
                >
                  {{ category }}
                </h6>
                <br /><br /><br />
                <h4 class="search-title">Filter by +</h4>
                <br />
                <div class="co">
                  <h5>Color</h5>
                  <span
                    class="circle"
                    v-for="(color, index) in colors"
                    :key="index"
                    :style="{ backgroundColor: color }"
                    @click="sortI(color)"
                  ></span>
                </div>
                <br /><br />
                <h5>Price Range</h5>
                <div class="price-range">
                  <input
                    type="range"
                    class="form-range"
                    min="0"
                    :max="priceRange[1]"
                    :value="priceRange[0]"
                    @input="setPrice"
                  />
                  <div class="price-labels d-flex justify-content-between">
                    <span>{{ priceRange[0] }}</span>
                    <span>{{ priceRange[1] }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div
          class="row col-xl-9 col-lg-9 col-md-12 col-sm-12 col-xs-12 text-center"
        >
          <div
            v-if="this.cards == 0"
            class="col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12"
          >
            <h4 style="margin-left: 9rem; margin-right: 9rem">
              Sorry, we can't find a product with this features
            </h4>
          </div>

          <Card :CardArray="slicedCards" />

          <div
            class="col-12 col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12 py-5"
          >
            <button
              type="button"
              @click="incCardNumber"
              class="btn btn-outline-secondary btn-lg btn-block"
            >
              More +
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import slider from "./slider.vue";
import Card from "./Card.vue";
import axios from "axios";
/* eslint-disable */
export default {
  name: "Grid",
  components: {
    slider,
    Card,
  },
  data() {
    return {
      cards: [],
      origin: [],
      showCards: 6,
      sortButton: "DEFAULT",
      searchText: "",
      categories: [],
      colors: [],
      priceRange: [],
    };
  },
  created() {
    this.fetchCards();
  },
  computed: {
    slicedCards() {
      return this.cards.slice(0, this.showCards);
    },
  },
  methods: {
    fetchCards() {
      axios
        .get("/api/product/findAllProduct")
        .then((response) => {
          this.cards = response.data;
          this.origin = response.data;
          // 获取所有类别
          const categories = [
            ...new Set(response.data.map((item) => item.productCategories.toLowerCase())),
          ];
          this.categories = categories;

          // 获取所有颜色
          const colors = [
            ...new Set(
              response.data.map((item) => item.productColor.toLowerCase())
            ),
          ];
          this.colors = colors;

          // 获取价格范围
          const prices = response.data.map((item) => item.productPrice);
          this.priceRange = [Math.min(...prices), Math.max(...prices)];
        })
        .catch((error) => {
          console.log(error);
        });
    },
    incCardNumber() {
      return (this.showCards += 6);
    },
    sortPriceAsc() {
      this.cards.sort((a, b) => a.productPrice - b.productPrice);
      this.sortButton = "PRICE (ASC)";
    },
    sortPriceDesc() {
      this.cards.sort((a, b) => b.productPrice - a.productPrice);
      this.sortButton = "PRICE (DESC)";
    },
    sortI(value) {
      this.cards = this.origin.filter(
        (item) =>
          item.productCategories === value || item.productColor === value
      );
      this.sortButton = value.toUpperCase();
    },
    setPrice(event) {
      const value = event.target.value;
      this.cards = this.origin.filter(
        (item) =>
          item.productPrice >= value && item.productPrice <= this.priceRange[1]
      );
    },
    search() {
      if (this.searchText.length > 0) {
        this.cards = this.origin.filter(
          (item) =>
            item.productName
              .toLowerCase()
              .includes(this.searchText.toLowerCase()) ||
            item.productCategories
              .toLowerCase()
              .includes(this.searchText.toLowerCase())
        );
      } else {
        this.fetchCards();
      }
    },
  },
};
</script>


<style>
.container.grid {
  min-height: 60rem;
}

.container.grid a {
  cursor: pointer !important;
}

.btn-light {
  color: black !important;
  background: white;
  border-radius: 0 !important;
  border: 1px solid grey !important;
}
.dropdown-menu {
  background-color: #eee;
  color: #2c3539;
}

.dropdown-menu > a:hover {
  background-color: #dae0e5;
}

.btn-outline-secondary {
  border-radius: 0 !important;
}

/*search options*/

.card-selector {
  color: #dcdcdc;
  height: 40rem;
  background: #2c3539 !important;
  box-shadow: 0 8px 6px 0 rgba(0, 0, 0, 0.1), 0 26px 70px 0 rgba(0, 0, 0, 0.69);
}

.search-title h6 {
  cursor: pointer;
}

.circle {
  height: 17px;
  width: 17px;
  border-radius: 50%;
  border: 0.7px solid #2c3539;
  display: inline-block;
  margin-left: 6px;
  cursor: pointer;
}

</style>
