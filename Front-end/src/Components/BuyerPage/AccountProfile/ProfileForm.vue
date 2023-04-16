<template>
    <div class="container my-2">

        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            

            <b-form-group id="uname-group" label="Username:" label-for="uname">
                <b-form-input id="uname" v-model="form.username" type="text" placeholder="Enter username"
                    required></b-form-input>
            </b-form-group>

            <!-- First name, last name -->
            <b-row>
                <b-col>
                    <b-form-group id="fname" label="First name:" label-for="first name">
                        <!-- <label for="first name">First Name</label> -->
                        <b-form-input id="first name" v-model="form.first_name" placeholder="First name" required>
                        </b-form-input>
                    </b-form-group>
                </b-col>
                <b-col>

                    <b-form-group id="lname" label="Last name:" label-for="last name">
                        <!-- <label for="first name">First Name</label> -->
                        <b-form-input id="last name" v-model="form.last_name" placeholder="Last name" required>
                        </b-form-input>
                    </b-form-group>

                </b-col>
            </b-row>

            <!-- Email -->
            <b-form-group id="email-group" label="Email address:" label-for="email">
                <b-form-input id="email" v-model="form.email" type="email" placeholder="Enter email"
                    required></b-form-input>
            </b-form-group>

            <!-- Tel -->
            <b-form-group id="tel-group" label="Tel number:" label-for="tel" >
                <b-input-group prepend="+41">
                    <b-form-input id="tel" v-model="form.tel" type="tel" placeholder="Enter your telephone number: xxxxxxxxx"
                pattern="[0-9]{9}"
                    required></b-form-input>
                </b-input-group>
            </b-form-group>

            <!-- Address, ##Password -->
            <!-- Shipping address for buyer -->
            
            <b-form-group id="address-group" label="Delivery address:" label-for="address">
                <b-form-input id="address" v-model="form.address" type="test" placeholder="Enter your street/no. and postal code/city."
                    ></b-form-input>
            </b-form-group>
        <div class="mt-5">
            <b-button class="mr-2" type="submit" variant="primary">Submit</b-button>
            <b-button type="reset" variant="danger">Reset</b-button>
            <b-button class="ml-auto logout" variant="outline-secondary" @click="onLogout">Logout</b-button>
        </div>
            
        </b-form>


    </div>
</template>


<script>
export default {
    data() {
        return {
            form: {
                email: 'bai.li@uzh.ch',
                username: 'shixian libai',
                last_name: 'Li',
                first_name: 'Bai',
                tel: '700000009',
                address: 'Rämistrasse 71, 8006 Zürich',
                food: null,
                checked: []
            },
            foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
            show: true
        }
    },
    methods: {
        onSubmit(event) {
            event.preventDefault()
            // alert(JSON.stringify(this.form))
        },
        onReset(event) {
            event.preventDefault()
            // Reset our form values
            this.form.email = ''
            this.form.username = ''
            this.form.first_name = ''
            this.form.last_name = ''
            this.form.address = ''
            this.form.tel = ''
            this.form.food = null
            this.form.checked = []
            // Trick to reset/clear native browser form validation state
            this.show = false
            this.$nextTick(() => {
                this.show = true
            })
        },
        onLogout() {
            this.$store.state.isLoggedIn = false;
            this.$router.push("/");
        }
    }
}
</script>
<style scoped>
.logout{
    margin-left: 10px !important;
}
</style>

