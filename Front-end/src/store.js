import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

// REMOVE CLASS FROM items ARRAY
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    infoPage:[],
    cartItems:[],
    isLoggedIn: false,
    isSeller:false,
    userAvatar:'',
    items: [
      {
        id:0,
        img: require('@/assets/1.jpg'),
        title: 'golf 243',
        price: 156,
        color: 'blue',
        type: 'golf'
      }, {
        id:1,
        img: require('@/assets/2.jpg'),
        title: 'tennis 54',
        price: 756,
        color: 'black',
        type: 'tennis'
      }, {
        id:2,
        img: require('@/assets/3.jpg'),
        title: 'hiking 34',
        price: 362,
        color: 'black',
        type: 'hiking'
      }, {
        id:3,
        img: require('@/assets/4.jpg'),
        title: 'fotal324',
        price: 505,
        color: 'black',
        type: 'chair'
      }, {
        id:4,
        img: require('@/assets/5.jpg'),
        title: 'golf-1',
        price: 243,
        color: 'red',
        type: 'golf'
      }, {
        id:5,
        img: require('@/assets/6.jpg'),
        title: 'Fotal-2',
        price: 44,
        color: 'red',
        type: 'chair'
      }, {
        id:6,
        img: require('@/assets/7.jpg'),
        title: 'hiking-34',
        price: 505,
        color: 'white',
        type: 'hiking'
      }, {
        id:7,
        img: require('@/assets/8.jpg'),
        title: 'bicycle-4364',
        price: 432,
        color: 'green',
        type: 'bicycle'
      },
      {
        id:8,
        img: require('@/assets/9.jpg'),
        title: 'bicycle-44',
        price: 390,
        color: 'white',
        type: 'bicycle'
      },
      {
        id:9,
        img: require('@/assets/10.jpg'),
        title: 'hiking-34',
        price: 756,
        color: 'black',
        type: 'hiking'
      },
      {
        id:10,
        img: require('@/assets/11.jpg'),
        title: 'hiking-23',
        price: 44,
        color: 'black',
        type: 'hiking'
      },
      {
        id:11,
        img: require('@/assets/12.jpg'),
        title: 'tennis-4234',
        price: 156,
        color: 'red',
        type: 'tennis'
      },
      {
        id:12,
        img: require('@/assets/13.jpg'),
        title: 'tennis-4234',
        price: 756,
        color: 'black',
        type: 'tennis'
      },
      {
        id:13,
        img: require('@/assets/14.jpg'),
        title: 'hiking-4234',
        price: 756,
        color: 'blue',
        type: 'hiking'
      },
      {
        id:14,
        img: require('@/assets/table-1.jpg'),
        title: 'bicycle-32',
        price: 756,
        color: 'blue',
        type: 'bicycle'
      }
    ]
  },
  plugins: [createPersistedState()],
  getters: {
    itemsNumber(state){  // Cart Component
      return state.cartItems.length
    },
    totalPrice(state) { // Cart Component
      if (state.cartItems.length != 0){
       return state.cartItems.reduce((a, b) => {
         return (b.price == null ) ? a : a + b.price
       }, 0)
      }
    },
    infoLength(state) { // Info Component
      if(state.infoPage.length > 0) {
        return state.infoPage.splice(0, 1)
      }
    }
  },
  mutations: {
    inCart(state, n) { // Cart Component
      return state.cartItems.push(n)
    },
    outCart(state, n) { // Cart Component
      let index = state.cartItems.findIndex(x => x.id === n)
      return state.cartItems.splice(index, 1)
    },
    addtoInfo(state, n) { // Info Component
       return state.infoPage.push(n)
    }
  },
})
