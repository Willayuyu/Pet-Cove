<template>
  <div>
    <b-table
      :items="buyerOrderDetailList"
      :fields="fields"
      :per-page="perPage"
      :current-page="currentPage"
    >
      <template #cell(details)="row">
        <b-button size="sm" @click="showDetails(row.item)"> Details </b-button>
      </template>
    </b-table>
    <b-card class="details" v-if="selectedItem" header="Details">
      <b-list-group>
        <b-list-group-item
          v-if="key in wordmapping"
          v-for="(value, key) in selectedItem"
          :key="key"
        >
          <div class="d-flex justify-content-between">
            <div>{{ mapWords(key) }}:</div>
            <div class="text-right">
              {{ value }}
            </div>
          </div>
        </b-list-group-item>
        <b-list-group-item>
          <b-card no-body header="Products">
            <b-list-group flush>
              <b-list-group-item
                href="#"
                v-for="product in buyerOrderItems"
                :key="product.name"
              >
                <div class="d-flex justify-content-between">
                  <div>{{ product.name }}</div>
                  <div class="text-right">
                    {{ product.count }} x {{ product.price }} =
                    {{ product.count * product.price }}
                  </div>
                </div>
              </b-list-group-item>
            </b-list-group>
          </b-card>
        </b-list-group-item>
      </b-list-group>
      <b-button v-if = !isCancelled(selectedItem) size="sm" @click="cancelOrder(selectedItem)"> Cancel Order </b-button>
    </b-card>
    <b-pagination
      :total-rows="totalRows"
      :per-page="perPage"
      v-model="currentPage"
    />
  </div>
</template>
  
<script>
import axios from "axios";
export default {
  components: {},
  data() {
    return {
      buyerOrderDetailList: [],
      sellerOrderItemList: [],
      buyerOrderItems: [],
      items: [
        {
          id: "01",
          price: 29,
          status: "Delivered",
          username: "user01",
          shippingAddress: "8001",
          products: [
            {
              name: "Sports shoes, fashion",
              link: "http://www.example.com",
              count: 1,
              price: 19,
            },
            {
              name: "Sports shoes, old",
              link: "http://www.example.com",
              count: 2,
              price: 10,
            },
          ],
        },
        {
          id: "02",
          price: 53,
          status: "Pending Shipment",
          username: "user02",
          shippingAddress: "8002",
        },
        {
          id: "03",
          price: 56,
          status: "Shipping in Progress",
          username: "user03",
          shippingAddress: "8003",
        },

        // ... more items
      ],
      fields: [
        // { key: "id", label: "No.", sortable: true },
        { key: "createdDate", label: "Created Date", sortable: true },
        { key: "cost", label: "Cost", sortable: true },
        { key: "status", label: "Status", sortable: true },
        { key: "details", label: "", sortable: false },
      ],
      wordmapping: {
        // id: "No.",
        // createdDate: ""
        cost: "Cost (dollar)",
        status: "Status",
        // username: "User",
        shippingAddress: "Shipping Addiress",
        products: "Products",
      },
      perPage: 10,
      currentPage: 1,
      selectedItem: null,
      showCancelled: true,
    };
  },
  mounted() {
    this.getOrderList();
    
  },
  computed: {
    totalRows() {
      return this.items.length;
    },
    mapWords() {
      return function (key) {
        // if (key in this.wordmapping.keys()) {
        return this.wordmapping[key];
        // }
      };
    },
    
  },
  methods: {
    getOrderList() {
        if (this.$store.state.isSeller){
        axios
              .get("/api/order/SellerGetOrderList", {
                  params: {
                      sellerId: this.$store.state.userId,

                  },
              })
              .then((response) => {
                  this.sellerOrderItemList = response.data; 
                  this.$store.state.address = this.profile.address;
              })
              .catch((error) => {
                  console.log(error);
              });
      }else {
        axios
              .get("/api/order/BuyerGetOrderList", {
                  params: {
                      buyerId: this.$store.state.userId,

                  },
              })
              .then((response) => {
                  this.buyerOrderDetailList = response.data;
              })
              .catch((error) => {
                  console.log(error);
              });
      }
    },
    cancelOrder(item) {
      axios
            .get("/api/order/BuyerCancelOrder", {
              params:{
                  orderId: item.id,
              }
            })
            .then((response) => {
              item.state = "Cancelled";
              this.showCancelled = false;
              // location.reload()
              this.getOrderList();
              alert("Cancel Successfully!");
            })
            .catch((error) => {
                console.log(error);
            });
    },
    isCancelled(selectedItem) {
      console.log(this.showCancelled)
      return selectedItem.status == "Cancelled" && this.showCancelled;
    },
    showDetails(item) {
      
      this.selectedItem = item;

      axios
            .get("/api/order/GetOneOrder", {
                params: {
                    orderId: item.id,
                },
            })
            .then((response) => {
                this.buyerOrderItems = response.data.products;
                console.log(this.buyerOrderItems);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    queryOrderList() {

    }
    // {
    //   "id": 10,
    //     "buyerId": 1,
    //     "sellerId": 2,
    //     "cost": 40.0,
    //     "createdDate": "12-05-2023",
    //     "createdTime": "12-05-2023 17:45",
    //     "status": "Pending Shipment",
    //     "shippingAddress": "Bellevue"
    // }
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
  


<!-- 
<template>
    <div>
      <b-table :items="items" :fields="fields" :per-page="perPage" :current-page="currentPage">
        <template #cell(expand)="row">
          <button @click="toggleExpand(row.item.id)">
            {{ row.item.isExpanded ? '收起' : '展开' }}
          </button>
        </template>
        <template #cell(expanded)="row">
          <transition name="expand">
            
                <div class="item-content" v-show="row.item.isExpanded">
                <b-card>
                row.item.description
                </b-card>
                     
                </div>
          </transition>
        </template>
      </b-table>
      <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" />
    </div>
  </template>
  
  <script>
  export default {
    components: {
    },
    data() {
      return {
        items: [
          { id: '01', price: 23, status: 'Completed', description: 'Some description', isExpanded: false },
          { id: '02', price: 53, status: 'Started', description: 'Some description', isExpanded: false },
          { id: '03', price: 56, status: 'Delivery', description: 'Some description', isExpanded: false },
          // ... more items
        ],
        fields: [
          { key: 'id', label: 'ID' },
          { key: 'price', label: 'Price' },
          { key: 'status', label: 'Status' },
          { key: 'expand', label: '', sortable: false },
          { key: 'expanded', label: '', sortable: false }
        ],
        perPage: 10,
        currentPage: 1
      }
    },
    computed: {
      totalRows() {
        return this.items.length
      }
    },
    methods: {
      toggleExpand(id) {
        this.items = this.items.map(item => {
          if (item.id === id) {
            item.isExpanded = !item.isExpanded
          } else {
            item.isExpanded = false
          }
          return item
        })
      }
    }
  }
  
  </script>
  
  <style>
  .expand-enter-active,
  .expand-leave-active {
    transition: all 0.5s;
  }
  
  .expand-enter,
  .expand-leave-to {
    opacity: 0;
    height: 0;
  }
  </style> -->
  
  