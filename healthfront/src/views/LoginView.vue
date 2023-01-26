<template>
  <div>
    <div class="row">
      <div class="col-4"></div>
      <div
        class="col-4 text-center"
        style="margin-top: 100px; border: 1px solid black; padding: 30px"
      >
        <span style="font-size: 40px">MyRoutine Login</span>
        <hr />
        <form>
          <input
            type="text"
            class="form-control"
            name="userId"
            v-model="userId"
          />
          <br />
          <input
            type="password"
            class="form-control"
            name="password"
            v-model="userPassword"
          /><br />
          <button
            style="position: relative; left: 150px"
            class="btn btn-success"
            @click.prevent="testMethod3"
          >
            Login
          </button>
          <br /><br />
          <span style="position: relative; left: 80px"
            >계정이 없습니까?
            <router-link to="/singup">회원가입하기</router-link></span
          >
          >
        </form>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
</template>
<script>
// import axios
import axios from 'axios'

export default {
  components: {},
  data() {
    return {
      userId: null,
      userPassword: null
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    testMethod3() {
      const id = this.userId
      const pw = this.userPassword
      axios // axios로 post 요청보냄
        .post('http://localhost:8081/auth/login', {
          userId: id,
          password: pw
        }) // res를 받아서 사용. res.data에 받은 data 있음. store의 login 메소드를 호출해서 store에 userId랑 token 값 넣어줌
        .then((res) => {
          if (res.status === 200) {
            this.$store.commit('login', res.data)
          }
        })
        .then((err) => {
          console.log(err)
        })
        .then(() => {})
    }
  }
}
</script>
