<template>
    <b-tab title="Add Products">
        <div>
            <form>
                <div class="form-group">
                    <label for="productName">Name:</label>
                    <input type="text" class="form-control" id="productName" v-model="productData.productName" required />
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
                    <input type="number" class="form-control" id="productNum" v-model="productData.productNum" required />
                </div>
                <div class="form-group">
                    <label for="productCategories">Category:</label>
                    <input type="text" class="form-control" id="productCategories" v-model="productData.productCategories"
                        required />
                </div>
                <div class="form-group">
                    <label for="productColor">Color:</label>
                    <input type="text" class="form-control" id="productColor" v-model="productData.productColor" required />
                </div>
                <div class="form-group">
                    <label for="productImage">Image:</label>
                    <input type="file" class="form-control-file" id="productImage" @change="previewImage" required />
                    <img :src="imageDataUrl" class="preview-image" v-if="imageDataUrl" />
                </div>
                <button type="submit" class="btn btn-primary" @click.prevent="addProduct">
                    Add
                </button>
            </form>
        </div>
    </b-tab>
</template>
  
<script>
/* eslint-disable */
export default {
    data() {
        return {
            productData: {
                productName: "",
                productDescription: "",
                productPrice: 0,
                productNum: 0,
                productCategories: "",
                productColor: "",
                sellerId: this.$store.state.userId,
                productImage:''
            },
            imageDataUrl: null,
        };
    },
    methods: {
        //Handles the addition of a new product.
        async addProduct() {
            if (!this.isFormValid()) {
                alert("Please fill in all fields.");
                return;
            }
            try {
                console.log(this.productData)
                await this.$axios.post("/api/product/insertProduct", this.productData);
                alert("Product added successfully.");
                this.productData.productName = "";
                this.productData.productDescription = "";
                this.productData.productPrice = 0;
                this.productData.productNum = 0;
                this.productData.productCategories = "";
                this.productData.productColor = "";
                this.imageDataUrl = null;

            } catch (error) {
                console.error(error);
            }
        },
        //Checks if all the required form fields are filled in and if the imageDataUrl is available.
        isFormValid() {
            return (
                this.productData.productName &&
                this.productData.productDescription &&
                this.productData.productPrice &&
                this.productData.productNum &&
                this.productData.productCategories &&
                this.productData.productColor &&
                this.imageDataUrl
            );
        },
        //Handles the previewing of the selected image.
        previewImage(event) {
            const file = event.target.files[0];
            if (!file) {
                return;
            }
            this.productData.productImage = URL.createObjectURL(file);
            console.log(this.productData.productImage)
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                this.imageDataUrl = reader.result;
                
            };
        },
    },
};
</script>
<style>
.preview-image {
    max-width: 200px;
    /* 设置最大宽度 */
    max-height: 200px;
    /* 设置最大高度 */
}
</style>
