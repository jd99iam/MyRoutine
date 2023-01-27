<template>
  <div>
    <div>login? : {{ this.$store.state.loginStore.isLogin }}</div>
    <button @click="showRoutine">귀요미버튼뿌뿌</button>
    여기다 달력에 루틴 넣으면됨
    <div class="row">
      <div class="col-6">test1</div>
      <div class="col-6">test2</div>
    </div>
    <div>{{ routines }}</div>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  components: {},
  data() {
    return {
      routines: null
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    showRoutine() {
      if (this.$store.state.loginStore.isLogin === false) {
        alert('로그인하세요')
      } else {
        var vm = this
        const userPK = this.$store.state.loginStore.id
        const config = {
          hedaers: {
            Authorization: 'Bearer ' + this.$store.state.loginStore.token
          }
        }
        axios
          .get('http://localhost:8081/routine/' + userPK, config)
          .then((res) => {
            vm.routines = res.data
          })
      }
    }
  }
}
</script>
<style></style>
