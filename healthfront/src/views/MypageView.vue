<template>
  <div>
    <div class="row" style="margin-top: 50px">
      <div class="col-4">
        <div
          class="card"
          style="width: 18rem; margin-left: 60px; border: 3px solid black"
        >
          <img src="@/assets/malwang.png" class="card-img-top" alt="..." />
          <div class="card-body">
            <h5
              class="card-title text-center"
              style="font-size: 30px; text-shadow: 1px 1px 2px black"
            >
              {{ this.$store.state.loginStore.nickname }}
            </h5>
            <p class="card-text text-center">
              <span v-if="userHeight != null" style="font-size: 20px"
                ><b>{{ userHeight }} cm</b></span
              >
              <span v-if="userWeight != null" style="font-size: 20px">
                / <b>{{ userWeight }} kg</b></span
              >
              <span v-for="(exercise, i) in userExerciseType" :key="i">
                <br />{{ exercise }}
              </span>
            </p>
          </div>
        </div>
      </div>
      <div class="col-6">
        <table
          class="table table-primary"
          style="font-size: 20px; border: 3px solid black"
        >
          <!-- <thead style="font-size: 25px">
            <tr>
              <th>날짜</th>
              <th>루틴</th>
              <th>
                <router-link to="/routinePost" @click="routinePost"
                  >생성하기</router-link
                >
              </th>
            </tr>
          </thead> -->
          <tbody>
            <tr>
              <td class="text-center">
                <div class="row">
                  <div class="col-8 text-end">
                    <input
                      v-model="selectedDate"
                      type="date"
                      style="font-size: 25px; border: 2px solid black"
                    />
                  </div>
                  <div class="col-4 text-end">
                    <button
                      class="btn btn-primary"
                      style="border: 2px solid gray"
                    >
                      <router-link
                        to="/routinePost"
                        @click="routinePost"
                        style="font-size: 20px; color: white"
                        ><b>생성하기</b></router-link
                      >
                    </button>
                  </div>
                </div>
              </td>
            </tr>
            <tr v-for="(routine, i) in routines" :key="i">
              <td v-if="checkDateDistance(selectedDate, routine.date)">
                <div id="carouselExample" class="carousel slide">
                  <div class="carousel-inner">
                    <div class="carousel-item active">
                      <img
                        src="@/assets/malwang.png"
                        class="d-block w-100"
                        alt="..."
                      />
                    </div>
                    <div class="carousel-item">
                      <img
                        src="@/assets/malwang.png"
                        class="d-block w-100"
                        alt="..."
                      />
                    </div>
                    <div class="carousel-item">
                      <img
                        src="@/assets/malwang.png"
                        class="d-block w-100"
                        alt="..."
                      />
                    </div>
                  </div>
                  <button
                    class="carousel-control-prev"
                    type="button"
                    data-bs-target="#carouselExample"
                    data-bs-slide="prev"
                  >
                    <span
                      class="carousel-control-prev-icon"
                      aria-hidden="true"
                    ></span>
                    <span class="visually-hidden">Previous</span>
                  </button>
                  <button
                    class="carousel-control-next"
                    type="button"
                    data-bs-target="#carouselExample"
                    data-bs-slide="next"
                  >
                    <span
                      class="carousel-control-next-icon"
                      aria-hidden="true"
                    ></span>
                    <span class="visually-hidden">Next</span>
                  </button>
                </div>
                <hr />
                <div class="row text-end" style="margin-right: 10px">
                  <span style="font-size: 20px">
                    <b>{{ routine.date }}</b></span
                  >
                </div>
                <br />
                {{ routine.routine }}
                <br /><br />

                <span>
                  <!-- <router-link
                    to="/routinePatch"
                    @click="routineStore"
                    :routineId="routine.id"
                    >수정</router-link
                  > -->
                  <button
                    style="margin-left: 430px"
                    class="btn btn-primary"
                    @click="routineStore"
                    :routineId="routine.id"
                  >
                    수정
                  </button>
                  <button
                    style="margin-left: 5px"
                    class="btn btn-primary"
                    :routineId="routine.id"
                    @click="deleteRoutine"
                  >
                    삭제
                  </button>
                </span>
              </td>
            </tr>
          </tbody>
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
      routines: null,
      userHeight: null,
      userWeight: null,
      userExerciseType: [],
      selectedDate: null
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getDateString()
    this.showRoutine()
    this.getUserInformation()
  },
  unmounted() {},
  methods: {
    checkDateDistance(d11, d22) {
      const d1 = new Date(d11)
      const d2 = new Date(d22)
      console.log('d1 : ' + d1 + ' and d2 : ' + d2)
      const div = 1000 * 60 * 60 * 24
      const d =
        (d1.getTime() - d2.getTime()) / div > 0
          ? (d1.getTime() - d2.getTime()) / div
          : (d2.getTime() - d1.getTime()) / div
      console.log(d)
      if (d >= 8) {
        return false
      } else {
        return true
      }
    },
    stringToDate(str) {
      return new Date(str)
    },
    getDateString() {
      const date = new Date()
      this.selectedDate =
        date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
    },
    getUserInformation() {
      var vm = this
      const userPK = this.$store.state.loginStore.id
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get('http://localhost:8081/auth/show/' + userPK, config)
        .then((res) => {
          vm.userHeight = res.data.height
          vm.userWeight = res.data.weight
          vm.userExerciseType = res.data.exerciseType
        })
    },
    showRoutine() {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
        document.location = 'http://localhost:8080/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
      } else {
        const vm = this
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
      this.$store.commit('setRoutineStore', {
        ruid: this.$store.state.loginStore.id,
        rid: event.target.getAttribute('routineId')
      })
      document.location = '/routinePatch'
    },
    deleteRoutine(event) {
      const userPK = this.$store.state.loginStore.id
      const rid = event.target.getAttribute('routineId')
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
<style>
a {
  text-decoration: none;
  color: black;
}
button {
  border: 2px solid gray;
}
</style>
