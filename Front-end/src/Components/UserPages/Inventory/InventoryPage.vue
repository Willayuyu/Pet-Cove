<template>

    <b-tab :active="activeTabIndex === 3" title-item-class="mt-1" title="Inventory Management">
        <b-card header="Inventory Management">
        <b-tabs content-class="mt-3" v-model="activeTab">
            <MyProducts />
            <AddProducts />           
        </b-tabs>
    </b-card>
    </b-tab>
</template>

<script>

import MyProducts from './MyProducts.vue';
import AddProducts from './AddProducts.vue';
export default {
    components: {
        MyProducts,
        AddProducts,
    }
}
</script>