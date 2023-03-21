<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-3 bg-light">
        <ul class="nav flex-column">
          <li class="nav-item">
            <router-link to="/account-profile" class="nav-link">Account Profile</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/orders-management" class="nav-link">Orders Management</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/inventory-management" class="nav-link">Inventory Management</router-link>
            <ul class="nav flex-column ml-3">
              <li class="nav-item">
                <router-link to="/add-products" class="nav-link">Add Products</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/update-products" class="nav-link">Update Products</router-link>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="col-9">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserHomepage',
  components: {},
  data() {
    return {};
  },
};
</script>

<style scoped>
/* add custom styles here */
</style>
