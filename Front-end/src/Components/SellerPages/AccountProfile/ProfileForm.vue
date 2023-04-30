<template>
    <div class="container my-2">
        <b-form ref="form" @submit="onSubmit" @reset="onReset" v-if="loadedData">
            <b-form-group id="uname-group" label="Username:" label-for="uname">
                <b-form-input id="uname" v-model="form.username" type="text" placeholder="Enter username"
                    required></b-form-input>
            </b-form-group>

            <!-- First name, last name -->
            <b-row>
                <b-col>
                    <b-form-group id="fname" label="First name:" label-for="first name">
                        <b-form-input id="first name" v-model="form.first_name" placeholder="First name"
                            required></b-form-input>
                    </b-form-group>
                </b-col>
                <b-col>
                    <b-form-group id="lname" label="Last name:" label-for="last name">
                        <b-form-input id="last name" v-model="form.last_name" placeholder="Last name"
                            required></b-form-input>
                    </b-form-group>
                </b-col>
            </b-row>

            <!-- Email -->
            <b-form-group id="email-group" label="Email address:" label-for="email">
                <b-form-input id="email" v-model="form.email" type="email" placeholder="Enter email"
                    required></b-form-input>
            </b-form-group>

            <!-- Tel -->
            <b-form-group id="tel-group" label="Tel number:" label-for="tel">
                <b-input-group prepend="+41">
                    <b-form-input id="tel" v-model="form.tel" type="tel"
                        placeholder="Enter your telephone number: xxxxxxxxx" pattern="[0-9]{9}" required></b-form-input>
                </b-input-group>
            </b-form-group>

            <!-- Address, ##Password -->
            <!-- Shipping address for buyer -->

            <b-form-group id="address-group" label="Delivery address:" label-for="address">
                <b-form-input id="address" v-model="form.address" type="test"
                    placeholder="Enter your street/no. and postal code/city."></b-form-input>
            </b-form-group>

            <div class="mt-5">
                <b-button class="mr-2" type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
                <b-button class="ml-auto logout" variant="outline-secondary" @click="onLogout">Logout</b-button>
            </div>
        </b-form>
        <b-spinner v-else></b-spinner>
    </div>
</template>

<script>
import axios from "axios";
/* eslint-disable */
export default {
    data() {
        return {
            loadedData: false,
            form: {
                username: "",
                first_name: "",
                last_name: "",
                email: "",
                tel: "",
                address: "",
                flag: 1,
            },
            profile: null,
        };
    },
    mounted() {
        axios
            .get("/GetProfile", {
                params: {
                    username: this.$store.state.username,
                },
            })
            .then((response) => {
                this.profile = response.data; // 将响应数据保存在组件中
                this.form.username = this.profile.uname;
                this.form.first_name = this.profile.fname;
                this.form.last_name = this.profile.lname;
                this.form.email = this.profile.email;
                this.form.tel = this.profile.phone;
                this.form.address = this.profile.address;
                this.form.flag = this.profile.flag;
                this.loadedData = true;
            })
            .catch((error) => {
                console.log(error);
            });
    },
    methods: {
        onSubmit(event) {
            event.preventDefault();
            // example
            axios
                .post("/UpdateProfile", this.form)
                .then((response) => {
                    console.log(response);
                    alert("Profile updated successfully!");
                })
                .catch((error) => {
                    console.log(error);
                    alert("Profile update failed!");
                });
        },
        onReset(event) {
            event.preventDefault();
            // Reset our form values
            this.form.email = "";
            this.form.username = "";
            this.form.first_name = "";
            this.form.last_name = "";
            this.form.address = "";
            this.form.tel = "";
            // Trick to reset/clear native browser form validation state
            this.$nextTick(() => {
                this.$refs.form.resetValidation();
            });
        },
        onLogout() {
            this.$store.state.isLoggedIn = false;
            this.$router.push("/");
        },
    },
};
</script>
<style scoped>
.logout {
    margin-left: 10px !important;
}
</style>
