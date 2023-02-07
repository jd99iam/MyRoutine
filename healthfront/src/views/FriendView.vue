<template>
  <div>
    show val = {{ this.val }}
    <ul>
      <li v-for="list in lists" v-bind:key="list">
        <button @click=";[showRoutine(list.id), setVal(list.id)]">
          이름 : {{ list.name }} || 나이 : {{ list.age }} || 식별번호(ID) : {{ list.id }}
        </button>
      </li>
    </ul>
    <table>
      <tr v-for="d in detailList" :key="d">
        <td>{{ d.id }}</td>
        ///
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
    </table>
  </div>
</template>
<script>
// import { thisTypeAnnotation } from '@babel/types'
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      routineUserId: null,
      lists: [],
      detailList: [],
      val: 0
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.showFriends()
  },
  unmounted() {},
  methods: {
    setVal(id) {
      this.val = id
    },
    showRoutine(id) {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
        document.location = 'http://localhost:8080/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
      } else {
        const userPK = id
        const config = {
          headers: {
            Authorization: 'Bearer ' + this.$store.state.loginStore.token
          }
        }
        axios
          .get('http://localhost:8081/routine/' + userPK, config)
          .then((res) => {
            this.detailList = res.data
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
    }
  }
}
</script>
