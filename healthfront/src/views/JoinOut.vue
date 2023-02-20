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
          ><b>회원탈퇴</b></span
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
            @click.prevent="deletes"
          >
            회원탈퇴하기
          </button>
          <button
            style="position: relative; left: 130px; border: 2px solid black"
            class="btn btn-primary"
            @click="deletes2"
          >
            회원탈퇴하기dfdf
          </button>
          <br />
        </form>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
</template>
<script>
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
    deletes() {
      const id = this.userId
      const pw = this.userPassword
      console.log(id)
      console.log(pw)
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .delete('http://localhost:8081/auth/delete/' + id + '/' + pw, config)
        .then((res) => {
          console.log(res)
          alert('회원탈퇴가 정상적으로 이루어졌습니다.')
          location.href = '/'
        })
    },
    deletes2() {
      this.$router.push('/')
    }
  }
}
</script>
