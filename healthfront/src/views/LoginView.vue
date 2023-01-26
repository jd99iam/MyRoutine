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
    <!-- test -->
    <div>{{ userId }}, {{ userPassword }}</div>
    <br />
    <!--testbutton-->
    <button @click="testMethod">루틴보여주기테스트get</button>
    <button @click="testMethod2">루틴생성하기테스트post</button>
    <button @click="testMethod3">로그인테스트</button><br />
    <div>{{ this.$store.state.userId }},{{ this.$store.state.token }}</div>
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
    loginSubmit() {
      console.log('test')
      console.log(
        'request id' + this.userId + ' reqeust pw' + this.userPassword
      )
      axios
        .post('http://localhost:8081/auth/login', {
          userId: this.userId,
          password: this.password
        })
        .then((res) => {
          console.log(res.data)
        })
        .then((err) => {
          console.log(err)
        })
        .then(() => {
          console.log('nothing')
        })
    },
    testMethod() {
      axios.get('http://localhost:8081/routine/4').then((res) => {
        console.log(res.data)
      })
    },
    testMethod2() {
      axios
        .post('http://localhost:8081/routine/4', {
          date: '2023-01-09',
          routine: '루틴생성하기테스트프론트'
        })
        .then((res) => {
          console.log(res.data)
        })
    },
    testMethod3() {
      const id = this.userId
      const pw = this.userPassword
      axios
        .post('http://localhost:8081/auth/login', {
          userId: id,
          password: pw
        })
        .then((res) => {
          if (res.status === 200) {
            this.$store.commit('login', res.data)
          }
        })
        .then((err) => {
          console.log(err)
        })
    }
  }
}
</script>
