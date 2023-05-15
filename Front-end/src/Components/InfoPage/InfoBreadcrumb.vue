<template>
  <div>
    <nav class="row justify-content-start" aria-label="breadcrumb">
      <ol class="breadcrumb" v-for="item in product" :key="item.productId">
        <li class="breadcrumb-item">
          <router-link to="/">Home</router-link>
        </li>
        <li class="breadcrumb-item"><router-link to="/products">Products</router-link></li>
        <li class="breadcrumb-item">{{ item.productCategories }}</li>
        <li class="breadcrumb-item active">{{ item.productName }}</li>
      </ol>
    </nav>
  </div>
</template>

<script>
export default {
  props: {
    product: {
      type: Object,
      required: true
    },
  },
  name: 'InfoBreadcrumb'
}
</script>

<style scoped>
.breadcrumb{
  background: inherit;
}

.breadcrumb>li>a {
  text-decoration: none;
  color: #2c3539 !important;
  font-size: 18px;
}
.breadcrumb>li>a:hover {
  text-decoration: underline;
}

.breadcrumb-item.active {
  text-decoration: none !important;
  color: #f2be00 !important;
}
</style>
