<template>

    <b-tab title-item-class="mt-1" title="Inventory Management">
        <p>I'm the tab with the very, very long title</p>
        <b-tabs content-class="mt-3" >
            <MyProducts />
            <AddProducts />
            
        </b-tabs>
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