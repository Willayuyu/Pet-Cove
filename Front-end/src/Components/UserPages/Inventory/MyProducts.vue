<template>
    <b-tab title="My Products" active>
        <div>
            <b-table :items="products" :fields="fields" :per-page="perPage" :current-page="currentPage">
                <template #cell(details)="row">
                    <b-button size="sm" @click="showDetails(row.item)">
                        Details
                    </b-button>
                </template>
            </b-table>
            <b-card class="details" v-if="selectedItem" header="Details">
                {{ selectedItem.productDescription }}
            </b-card>
            <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" />
        </div>
    </b-tab>
</template>
  
<script>
/* eslint-disable */
export default {
    data() {
        return {
            products: [], // 所有商品的列表
            selectedItem: null, // 当前选中的商品
            fields: [
                { key: "productName", label: "Name" },
                { key: "productPrice", label: "Price" },
                { key: "productNum", label: "Inventory" },
                { key: "productCategories", label: "Category" },
                { key: "details", label: "Details" },
            ],
            currentPage: 1,
            perPage: 10,
            totalRows: 0,
        };
    },
    mounted() {
        this.findAllProductsForSeller();
        setInterval(() => {
            this.findAllProductsForSeller();
        }, 5000);
    },
    methods: {
        // 查询卖家的所有商品
        async findAllProductsForSeller() {
            try {
                const response = await this.$axios.get(
                    "/api/product/findAllProductForSeller",
                    {
                        params: {
                            sellerId: this.$store.state.userId, // 从 store 中获取当前用户的 id 作为 sellerId
                        },
                    }
                );
                this.products = response.data;
                console.log(response.data);
                this.totalRows = response.data.length;
            } catch (error) {
                console.error(error);
            }
        },
        // 显示商品的详细信息
        showDetails(item) {
            this.selectedItem = item;
        },
    },
};
</script>
  

<style>
.details {
    min-height: 300px;
    margin-top: 3rem;
    margin-bottom: 5rem;
}
</style>