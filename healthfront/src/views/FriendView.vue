<template>
  <div>
    <ul>
      <li v-for="lists in lists" v-bind:key="lists">
        <button @click="[showRoutine(lists.id),setVal(lists.id)]">이름 : {{ lists.name }} || 나이 : {{ lists.age }} || 식별번호(ID) : {{ lists.id }}</button>
      </li>
    </ul>
    <table>
      <tr v-for="detailList in detailList" :key="detailList">
            <td>{{ detailList.date }}</td>
            <td>{{ detailList.routine }}</td>
          </tr>
      </table>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data () {
    return {
      lists: [],
      detailList: [],
      val: 0
    }
  },
  setup () {},
  created () {},
  mounted () {
    this.showFriends()
  },
  unmounted () {},
  methods: {
    setVal (id) {
      this.val = id
    },
    showRoutine (id) {
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
    showFriends () {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
        document.location = 'http://localhost:8080/login' // 로그인하지 않았을 경우 로그인 페이지로 이동시킴
      } else {
        const config = {
          headers: {
            Authorization: 'Bearer ' + this.$store.state.loginStore.token
          }
        }
        axios
          .get('http://localhost:8081/friend/show', config)
          .then((res) => {
            this.lists = res.data
          })
      }
    }
  }
}
</script>
