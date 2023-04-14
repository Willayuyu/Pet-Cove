import Vue from 'vue'
import Router from 'vue-router'

const Home = () => import('@/views/Home.vue');
const Products = () => import('@/views/Products.vue');
const Contact = () => import('@/views/Contact.vue');
const Info = () => import('@/views/Info.vue');
const Login = () => import('@/views/Login.vue');
const Register = () => import('@/views/Register.vue');

const SellerHomePage = () => import('@/views/Seller/SellerHomePage.vue')
const BuyerHomePage = () => import('./Components/BuyerPage/BuyerHomePage.vue')
const TestPage = () => import('@/views/Test/TestPage.vue')
const Checkout =()=>import('./Components/Checkout.vue')

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },{
      path: '/products',
      name: 'Products',
      component: Products
    },
    {
      path: '/contact',
      name: 'Contact',
      component: Contact
    },
    {
      path: '/info',
      name: 'Info',
      component: Info
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/sellerHomePage',
      name: 'SellerHomePage',
      component: SellerHomePage
    },
    {
      path: '/testPage',
      name: 'TestPage',
      component: TestPage
    },
    {
      path: '/buyerHomePage',
      name: 'BuyHomePage',
      component: BuyerHomePage
    },
    {
      path: '/checkout',
      name: 'Checkout',
      component: Checkout
    },
    
  ],
  mode: 'history'
})
