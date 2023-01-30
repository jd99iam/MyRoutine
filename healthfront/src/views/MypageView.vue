<template>
  <div>
    <div class="row text-center" style="margin-top: 50px; margin-bottom: 25px">
      <h1>{{ this.$store.state.loginStore.nickname }}님의 루틴</h1>
    </div>
    <hr />
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
        <table class="table table-primary">
          <tr>
            <th>날짜</th>
            <th>루틴</th>
            <th>
              <router-link to="/routinePost" @click="routinePost"
                >생성하기</router-link
              >
            </th>
          </tr>
          <tr v-for="(routine, i) in routines" :key="i">
            <td>{{ routine.date }}</td>
            <td>{{ routine.routine }}</td>
            <td>
              <router-link
                to="/routinePatch"
                @click="routineStore"
                :routineId="routine.id"
                >수정</router-link
              >
              <button :routineId="routine.id" @click="deleteRoutine">
                삭제</button
              >{{ routine.id }}
            </td>
          </tr>
        </table>
      </div>
      <div class="col-2"></div>
    </div>
    <br />
  </div>
</template>
<script>
import axios from 'axios'

export default {
  components: {
    // calendar
  },
  data() {
    return {
      routines: null
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.showRoutine()
  },
  unmounted() {},
  methods: {
    showRoutine() {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
        document.location = 'http://localhost:8080/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
      } else {
        var vm = this
        const userPK = this.$store.state.loginStore.id
        const config = {
          headers: {
            Authorization: 'Bearer ' + this.$store.state.loginStore.token
          }
        }
        axios
          .get('http://localhost:8081/routine/' + userPK, config)
          .then((res) => {
            vm.routines = res.data
          })
      }
    },
    routineStore(event) {
      console.log(event.target)
      this.$store.state.routineStore.routineId =
        event.target.getAttribute('routineId')
    },
    deleteRoutine(event) {
      const userPK = this.$store.state.loginStore.id
      var rid = event.target.getAttribute('routineId')
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .delete('http://localhost:8081/routine/' + userPK + '/' + rid, config)
        .then((res) => {
          alert('삭제 완료')
          console.log(res.data)
          document.location = 'http://localhost:8080/myPage'
        })
    }
  }
}
</script>
<style></style>
