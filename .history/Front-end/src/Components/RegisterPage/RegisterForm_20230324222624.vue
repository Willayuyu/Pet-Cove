<template>
  <section class="signUpSection">
    <div class="msg">
      <h4 class="welcome">Join us!</h4>
      <h6 class="instructions">Enter your sign up information</h6>
    </div>
    <div class="loginCard">
      <div class="loginForm">
        <form action="" class="login">
          <!-- <b-form-group class="inputLabel" id="uname-group" label="Username:" label-for="uname"></b-form-group>
              <b-form-input 
                id="uname" 
                class="name"
                type="text" 
                placeholder="Enter your username"
                required>
            </b-form-input> -->

          <label class="inputLabel" for="uname">Username</label>
          <input
            class="name"
            type="text"
            name="uname"
            placeholder="Enter your username"
          />

          <label class="inputLabel" for="fname">First name</label>
          <input
            class="name"
            type="text"
            name="fname"
            placeholder="Enter your first name"
          />
          <label class="inputLabel" for="lname">Last name</label>
          <input
            class="name"
            type="text"
            name="lname"
            placeholder="Enter your last name"
          />
          <label class="inputLabel" for="email">Email</label>
          <input
            class="user"
            type="text"
            name="email"
            placeholder="Enter your email"
          />
          <label class="inputLabel" for="tel">Telephone number</label>
          <input
            class="user"
            type="text"
            name="tel"
            placeholder="Enter your telephone number"
            required
          />
          <label class="inputLabel" for="password">Password</label>
          <input
            class="password"
            type="password"
            name="password"
            placeholder="Enter your password"
          />

          <!-- <div class="rememberMe">
              <input type="checkbox" name="remember" />
              <p class="message">
                I agree with <a href="">Terms</a> and <a href="">Privacy</a>
              </p>
            </div> -->

          <div class="rememberMe">
            <input type="checkbox" name="remember" v-model="identity" />
            <p class="message">Register as a seller.</p>
          </div>

          <!-- <button class="loginBtn" type="">Signup</button> -->
          <router-link
            class="loginBtn"
            to="/sellerHomePage"
            tag="button"
            v-if="(identity = true)"
          >
            Register
          </router-link>
          <router-link
            class="loginBtn"
            to="/sellerHomePage"
            tag="button"
            v-if="(identity = true)"
          >
            Register
          </router-link>
        </form>
        <p class="join">
          Already a member?
          <router-link class="toJoin" to="/login">Login</router-link>
        </p>
      </div>
      <div class="or">
        <span class="divider"> Or </span>
      </div>
      <div class="socialLogin">
        <button class="socialLoginBtn" type="">
          <i class="fa-brands fa-google"></i>Continue with Google
        </button>
        <button class="socialLoginBtn" type="">
          <i class="fa-brands fa-apple"></i>Continue with Apple
        </button>
        <button class="socialLoginBtn" type="">
          <i class="fa-brands fa-facebook-f"></i>Continue with Facebook
        </button>
      </div>
    </div>
  </section>
</template>
  
  <script>
export default {};
</script>
  
  <style scoped>
.signUpSection {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 0.8rem;
  margin: 1rem 0 2rem 0;
}
.msg {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.welcome {
  font-size: 2rem;
}
.instructions {
  color: var(--carbonLight);
}
.login {
  display: flex;
  flex-direction: column;
}
.inputLabel {
  margin-top: 0.5rem;
}
.user,
.password,
.name {
  display: flex;
  border: solid var(--carbon) 1px;
  border-radius: 2rem;
  padding: 0.5rem 1rem;
  appearance: none;
  outline: none;
  width: 20rem;
  font-size: 1.2rem;
  transition: 0.3s;
}
input::placeholder {
  color: var(--carbonLight);
  font-size: 0.9rem;
}
input:focus {
  box-shadow: 0 0 10px var(--blue);
}
.rememberMe {
  margin-top: 0.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.rememberMe p {
  margin: 0 0 0 0.2rem;
}
.loginBtn {
  border: solid 1px var(--blue);
  color: black;
  text-decoration: none;
  background-color: var(--blue);
  padding: 5px 15px;
  border-radius: 2rem;
  transition: 0.3s;
  margin: 0.5rem 0;
}
.loginBtn:hover {
  background-color: rgb(182, 221, 248);
  border: solid 1px var(--carbonLight);
}
.join {
  color: var(--carbonLight);
  font-size: 0.8rem;
  text-align: center;
}

.or {
  display: block;
  text-align: center;
  overflow: hidden;
  white-space: nowrap;
  margin: 0.5rem 0;
}
.divider {
  position: relative;
  display: inline-block;
}
.divider::after,
.divider::before {
  content: "";
  position: absolute;
  top: 50%;
  width: 9999px;
  height: 1px;
  background: var(--carbon);
}
.divider::after {
  left: 100%;
  margin-left: 1rem;
}
.divider::before {
  right: 100%;
  margin-right: 1rem;
}
.socialLogin {
  display: flex;
  flex-direction: column;
}
.socialLoginBtn {
  border: solid 1px var(--carbon);
  color: var(--carbon);
  text-decoration: none;
  background-color: white;
  padding: 5px 15px;
  border-radius: 2rem;
  transition: 0.3s;
  margin: 0.2rem 0;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  transition: 0.4s;
}
.socialLoginBtn:hover {
  background-color: var(--carbonLight);
  border: solid 1px var(--carbonLight);
}
</style>
  