<template>

    <b-tab title-item-class="mt-1" title="Inventory Management">
        <b-card header="Inventory Management">
        <b-tabs content-class="mt-3" >
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