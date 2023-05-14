<template>
    <div class="container my-5" >
        <div>
            <b-card no-body style="min-height: 1000px;">
                <div>
                    <b-tabs class="ml-2 my-4" content-class="mx-5 my-3" justified vertical lazy>
                        <AccountProfile/>
                        <OrderPage/>
                        <InventoryPage/>
                        <b-tab title-item-class="my-1" title="Coming soon" disabled><p>I'm a disabled tab!</p></b-tab>
                    </b-tabs>
                </div>
            </b-card>
        </div>
    </div>
</template>

<script>
import AccountProfile from './AccountProfile/AccountProfile.vue';
import OrderPage from './Orders/OrderPage.vue';
import InventoryPage from './Inventory/InventoryPage.vue';

export default {
    components: {
        AccountProfile,
        OrderPage,
        InventoryPage
    }
}

</script>

<style>

.tab {
    margin: 20px;
}


