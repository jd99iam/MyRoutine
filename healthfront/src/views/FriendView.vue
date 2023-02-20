<template>
  <div>
    <br />
    thisval = {{ this.val }}<br />
    <label style="font-size: 20px"
      ><strong>나의 친구 수 = {{ this.value }}</strong></label
    >
    <br />
    <label style="font-size: 20px"
      ><strong>나를 팔로우한 수 = {{ this.follow }}</strong></label
    >

    <br />
    <br />
    <table class="table">
      <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">이름</th>
          <th scope="col">나이</th>
          <th scope="col">식별번호</th>
          <th scope="col">루틴 복사하기</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="list in lists" v-bind:key="list">
          <th scope="row"></th>
          <td>{{ list.name }}</td>
          <td>{{ list.age }}</td>
          <td>{{ list.id }}</td>
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

    <table class="table" v-if="tablevalue === 1">
      <thead>
        <tr>
          <th>{{ who }}의 루틴 목록</th>
          <th scope="col">식별번호</th>
          <th scope="col">날짜</th>
          <th scope="col">루틴</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="d in detailList" :key="d">
          <td>{{ d.id }}</td>
          <td>{{ d.date }}</td>
          <td>{{ d.routine }}</td>
          <td>
            <button
              @click="routineCopy"
              type="button"
              class="btn btn-primary"
              :routineId="d.id"
            >
              루틴 복사
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <button type="button" class="btn btn-dark" @click="closetable">
      루틴 상세 보기 접기
    </button>
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
        document.location = 'http://localhost:8080/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
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
