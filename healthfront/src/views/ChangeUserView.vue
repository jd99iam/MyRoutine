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
          ><b>회원 정보 변경하기</b></span
        >
        <hr />
        <form>
          <table>
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
            <tr>
              <th>비밀번호확인</th>
              <td>
                <input
                  type="text"
                  class="form-control"
                  name="passwordok"
                  v-model="userPasswordok"
                  style="margin-left: 40px"
                />
              </td>
            </tr>
            <tr>
              <th>몸무게</th>
              <td>
                <input
                  type="text"
                  class="form-control"
                  name="weight"
                  v-model="weight"
                  style="margin-left: 40px"
                />
              </td>
            </tr>
            <tr>
              <th>키</th>
              <td>
                <input
                  type="text"
                  class="form-control"
                  name="height"
                  v-model="height"
                  style="margin-left: 40px"
                />
              </td>
            </tr>
          </table>
          <br />
          <button
            style="position: relative; left: 130px; border: 2px solid black"
            class="btn btn-primary" @click.prevent="changeData">
            회원탈퇴하기
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
// import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      password: null,
      weight: null,
      height: null
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    changeData() {
      const userPK = this.$store.state.loginStore.id
      const weight = this.weight
      const height = this.height
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .put(`http://localhost:8081/auth/update/${userPK}`, { weight: weight, height: height }, config)
        .then((res) => {
          console.log(res)
        })
    }
  }
}
</script>
