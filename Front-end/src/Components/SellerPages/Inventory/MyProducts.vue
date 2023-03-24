<template>

    <b-tab title="My Products" active>
        
        <div>
            <b-table :items="items" :fields="fields" :per-page="perPage" :current-page="currentPage">
                <template #cell(details)="row">
                    <b-button size="sm" @click="showDetails(row.item)">
                        Details
                    </b-button>
                </template>

            </b-table>
            <b-card class="details" v-if="selectedItem" header="Details">
                {{ selectedItem.description }}
            </b-card>
            <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" />
        </div>
    </b-tab>
</template>


<script>
export default {
    components: {
    },
    data() {
        return {
            items: [
                { 
                    id: '01', 
                    name: "Sports shoes, fashion",
                    price: 19, 
                    link: 'http://www.example.com',
                    description: 'This is a pair of stylish shoes.'
                }
                ,
                { 
                    id: '02', 
                    name: "Sports shoes, old",
                    price: 10, 
                    link: 'http://www.example.com',
                    description: 'This is a pair of old fashion shoes.'
                }
                ,

                // ... more items
            ],
            fields: [
                { key: 'id', label: 'ID', sortable: true},
                { key: 'name', label: 'Name', sortable: true },
                { key: 'price', label: 'Price', sortable: true },
                { key: 'link', label: 'Link'},
                { key: 'details', label: '', sortable: false },
            ],
            // wordmapping: {
            //     'id': 'No.',
            //     'price': 'Price (euros)',
            //     'status': 'Status',
            //     'username': "User",
            //     'shippingAddress': 'Shipping Addiress',
            //     'products': 'Products',
            // },
            perPage: 5,
            currentPage: 1,
            selectedItem: null
        }
    },
    computed: {
        totalRows() {
            return this.items.length
        },
        // mapWords() {
        //     return function(key){
        //         // if (key in this.wordmapping.keys()) {
        //             return this.wordmapping[key];
        //         // }   
        //     }
        // }
    },
    methods: {
        showDetails(item) {
            this.selectedItem = item
        }
    }
}

</script>

<style>

.details {
    min-height: 300px;
    margin-top: 3rem;
    margin-bottom: 5rem;

}

</style>