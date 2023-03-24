<template>

    <b-tab title-item-class="mt-1" title="Order Management">

        <b-card header="Order Management">
                    <OrderList/>
                </b-card>

    </b-tab>
</template>



<script>

import OrderList from './OrderList.vue';

export default{
    components: {
        OrderList
    }
}
</script>