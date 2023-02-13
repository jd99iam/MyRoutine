<template>
  <div>
    <div class="row">
      <div class="col-4"></div>
      <div
        class="col-4 text-center"
        style="
          margin-top: 100px;
          border: 4px solid black;
          padding: 30px;
          box-shadow: 1px 2px 2px gray;
        "
      >
        <span style="font-size: 40px; text-shadow: 1px 1px 2px gray"
          ><b>Login</b></span
        >
        <hr />
        <form>
          <table>
            <tr>
              <th>아이디</th>
              <td>
                <input
                  type="text"
                  class="form-control"
                  name="userId"
                  v-model="userId"
                  style="margin-left: 40px"
                />
              </td>
            </tr>
            <br />
            <tr>
              <th>비밀번호</th>
              <td>
                <input
                  type="password"
                  class="form-control"
                  name="password"
                  v-model="userPassword"
                  style="margin-left: 40px"
                />
              </td>
            </tr>
          </table>
          <br />
          <button
            style="position: relative; left: 130px; border: 2px solid black"
            class="btn btn-primary"
            @click.prevent="testMethod3"
          >
            Login
          </button>
          <br /><br />
          <span style="position: relative; left: 55px"
            ><b
              >계정이 없습니까?
              <router-link to="/signup">회원가입하기</router-link></b
            ></span
          >
        </form>
      </div>
      <div class="col-4"></div>
    </div>
    <br />
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
        })
        .then((res) => {
          if (res.status === 200) {
            this.$store.commit('login', res.data)
            console.log(res)
            location.href = '/'
          } else {
            console.log('응답코드 200 아님')
          }
        })
        .catch((err) => {
          console.log(err)
          alert('로그인 정보 불일치')
        })
    }
  }
}
</script>
