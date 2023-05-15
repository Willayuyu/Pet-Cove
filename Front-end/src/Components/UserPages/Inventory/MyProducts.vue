<template>
    <b-tab title="My Products" active @click="findAllProductsForSeller()">
        <div>
            <b-table :items="products" :fields="fields" :per-page="perPage" :current-page="currentPage">
                <template #cell(details)="row">
                    <div>
                        <b-button-group class="my-button-group">
                            <b-button size="sm" @click="showDetails(row.item)">Details</b-button>
                            <b-button size="sm" variant="primary" @click="editProduct(row.item)">Edit</b-button>
                            <b-button size="sm" variant="danger" @click="deleteProduct(row.item)">Delete</b-button>
                        </b-button-group>
                    </div>
                </template>
            </b-table>
            <b-card class="details" v-if="selectedItem" header="Details">
                <div>
                    <img :src="selectedItem.productImage" alt="Product Image" />
                    <p>{{ selectedItem.productDescription }}</p>
                </div>
            </b-card>
            <b-card v-if="selectedProductFields" header="Edit Product">
                <form>
                    <div class="form-group">
                        <label for="productName">Name:</label>
                        <input type="text" class="form-control" id="productName" v-model="productData.productName"
                            required />
                    </div>
                    <div class="form-group">
                        <label for="productDescription">Description:</label>
                        <textarea class="form-control" id="productDescription" v-model="productData.productDescription"
                            required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="productPrice">Price:</label>
                        <input type="number" class="form-control" id="productPrice" v-model="productData.productPrice"
                            required />
                    </div>
                    <div class="form-group">
                        <label for="productNum">Inventory:</label>
                        <input type="number" class="form-control" id="productNum" v-model="productData.productNum"
                            required />
                    </div>
                    <div class="form-group">
                        <label for="productCategories">Category:</label>
                        <input type="text" class="form-control" id="productCategories"
                            v-model="productData.productCategories" required />
                    </div>
                    <div class="form-group">
                        <label for="productColor">Color:</label>
                        <input type="text" class="form-control" id="productColor" v-model="productData.productColor"
                            required />
                    </div>
                    <div class="form-group">
                        <label for="productImage">Image:</label>
                        <input type="file" class="form-control-file" id="productImage" @change="previewImage" required />
                        <img :src="productData.productImage" class="preview-image" />
                    </div>
                    <button type="submit" class="btn btn-primary" @click.prevent="updateProduct">
                        Update
                    </button>
                </form>
            </b-card>
            <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" />
        </div>
    </b-tab>
</template>
<script>
/*eslint-disable*/
import axios from 'axios';
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
            productData: [], // 被更新的商品
            productOriginal: [],
            activeTab: "My Products", // 当前活跃的标签页
            selectedProductFields: false

        };
    },
    mounted() {
        this.findAllProductsForSeller();
        // setInterval(() => {
        //     this.findAllProductsForSeller();
        // }, 600000);
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
            this.selectedProductFields = null
        },
        // 编辑商品
        editProduct(item) {
            // TODO: Implement the editProduct method
            this.productData = item;
            this.productOriginal = item;
            this.selectedProductFields = true
            this.selectedItem = null
            console.log("Editing product", item);
        },
        // 删除商品
        deleteProduct(item) {
            // TODO: Implement the deleteProduct method
            this.selectedProductFields = null
            this.selectedItem = null
            console.log("Deleting product", item);
            axios.delete(`/api/product/${item.productId}/deleteProductById`)
                .then(response => {
                    console.log(response.data)
                    this.findAllProductsForSeller();
                    alert("delete successfully")
                })
                .catch(error => {
                    console.error(error)
                    // 删除商品失败
                })
        },
        updateProduct() {
            // 判断是否有更改数据
            axios
                .put(`/api/product/${this.productData.productId}/updateProductById`, this.productData)
                .then((response) => {
                    // 隐藏编辑面板
                    this.selectedProductFields = false;
                    // 重置被选中的商品
                    this.productData = null;
                    alert("update successfully")
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        checkProductDataChanged() {
            // 获取原始数据和新数据
            const originalData = this.productOriginal;
            const newData = this.productData;
            console.log(originalData, new data)
            let hasChanges = false;
            // 判断每个字段是否相同
            for (const key in newData) {
                if (newData[key] !== originalData[key]) {
                    hasChanges = true;
                    break;
                }
            }
            if (!hasChanges) {
                return;
            }

        },
    },
};
</script>
<style>
.details {
    min-height: 300px;
    margin-top: 3rem;
    margin-bottom: 5rem;
    image-rendering: crisp-edges;
}

.details img {
    max-width: 200px;
    margin-bottom: 1rem;
}

.my-button-group>.btn {
    margin-right: 10px;
}
</style>
