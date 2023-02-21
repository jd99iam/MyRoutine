<template>
  <div>
    <div class="row">
      <div class="col">
        <table class="table table-warning text-center">
          <thead>
            <tr>
              <th>팔로워</th>
              <th>
                {{ this.follow }}
              </th>
              <th>팔로잉</th>
              <th>
                {{ this.value }}
              </th>
            </tr>
          </thead>
        </table>
        <table class="table table-primary text-center">
          <thead>
            <tr>
              <th scope="col">이름</th>
              <th scope="col">나이</th>

              <th scope="col">루틴 복사하기</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="list in lists" v-bind:key="list">
              <td>{{ list.name }}</td>
              <td>{{ list.age }}</td>

              <td>
                <button
                  type="button"
                  class="btn btn-secondary"
                  @click=";[showRoutine(list.id, list.name), setvalue(list.id)]"
                >
                  루틴 상세보기
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col">
        <table class="table table-primary" v-if="tablevalue === 1">
          <tbody>
            <tr v-for="d in detailList" :key="d">
              <td>
                <div class="card" style="width: 40rem">
                  <div class="card-body">
                    <h5 class="card-title">{{ d.date }}</h5>
                    <p class="card-text">
                      {{ d.routine }}
                    </p>
                    <a
                      href="#"
                      class="btn btn-primary"
                      @click="routineCopy"
                      :routineId="d.id"
                      style="margin-left: 500px"
                      >루틴 복사</a
                    >
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <button
                  type="button"
                  class="btn btn-dark"
                  @click="closetable"
                  style="margin-left: 550px"
                >
                  접기
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <br />

    <br />
    <br />
  </div>
</template>
<script>
// import { thisTypeAnnotation } from '@babel/types'
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      follow: null,
      routineUserId: null,
      lists: [],
      detailList: [],
      value: 0,
      val: 0,
      who: null,
      tablevalue: 0
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.showFriends()
    this.setVal()
    this.setFollow()
  },
  unmounted() {},
  methods: {
    setFollow() {
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get('http://localhost:8081/friend/countfriends', config)
        .then((res) => {
          this.follow = res.data
        })
    },
    setvalue(id) {
      this.val = id
    },
    setVal() {
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get('http://localhost:8081/friend/showcount', config)
        .then((res) => {
          this.value = res.data
        })
    },
    showRoutine(id, name) {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
        document.location = 'http://localhost:8080/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
      } else {
        const userPK = id
        const username = name
        const config = {
          headers: {
            Authorization: 'Bearer ' + this.$store.state.loginStore.token
          }
        }
        axios
          .get('http://localhost:8081/routine/' + userPK, config)
          .then((res) => {
            this.detailList = res.data
            this.who = username
            this.tablevalue = 1
          })
      }
    },
    showFriends() {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
        document.location = 'http://localhost:8081/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
      } else {
        const config = {
          headers: {
            Authorization: 'Bearer ' + this.$store.state.loginStore.token
          }
        }
        axios.get('http://localhost:8081/friend/show', config).then((res) => {
          this.lists = res.data
        })
      }
    },
    routineCopy(event) {
      alert(event.target.getAttribute('routineId'))
      this.$store.commit('setRoutineStore', {
        ruid: this.val,
        rid: event.target.getAttribute('routineId')
      })

      document.location = 'http://localhost:8080/routineCopy'
    },
    closetable() {
      this.tablevalue = 0
    }
  }
}
</script>
