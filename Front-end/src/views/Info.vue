<template >
  <div class="container py-5" style="padding-top:70px;">

    <InfoBreadcrumb :information="information"/>
    <InfoBox :information="information"/>
    <InfoText />

    <div class="related-item">
      <hr>
      <h6 class="pb-4">RELATED PRODUCTS</h6>
      <Card :CardArray="sliceRelatedItems" />
    </div>

  </div>
</template>

<script>
import InfoBreadcrumb from '@/Components/InfoPage/InfoBreadcrumb.vue'
import InfoBox from '@/Components/InfoPage/InfoBox.vue'
import InfoText from '@/Components/InfoPage/InfoText.vue'
import Card from '@/Components/ProductsPage/Card.vue'

/* eslint-disable */
import axios from "axios";

export default {
  name:'Info',
  components: {
    InfoBreadcrumb, InfoBox, InfoText, Card
  },
  data() {
    return {
      product: [],
    }
  },
  mounted() {
    this.getProductDetails(this.$route.params.productId);
  },
  methods:{
    getProductDetails(productId){
      axios.get(`/api/product/findProductDetails?productId=${productId}`)
        .then(response => {
          this.product = response.data;
          console.log(product); // should log the product details
          this.information = response.data.productDescription
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
}
</script>

<style scoped>
hr {
width: 50px;
border-bottom: 1px solid black;
}
.related-item{
  padding-left: 8rem;
  padding-right: 8rem;
  height: auto;
  text-align: center;
}
</style>
