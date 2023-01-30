<template>
  <div>
    <label for="exampleInputEmail1" class="form-label">날짜</label>
    <input class="form-control" v-model="date" />
    <label for="exampleInputEmail1" class="form-label">루틴</label>
    <input class="form-control" v-model="content" />
    <button @click="submitPatch">수정완료</button>
    <br />
    테스트 <br />
    {{ date }}, {{ content }}, {{ this.$store.state.routineStore.id }}
  </div>
</template>
<script>
import axios from 'axios'

export default {
  components: {},
  data() {
    return {
      date: null,
      content: null
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getRoutine()
  },
  unmounted() {},
  methods: {
    getRoutine() {
      var vm = this
      var rid = this.$store.state.routineStore.routineId
      const userPK = this.$store.state.loginStore.id
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get('http://localhost:8081/routine/' + userPK + '/' + rid, config)
        .then((res) => {
          vm.date = res.data.date
          vm.content = res.data.routine
        })
    },
    submitPatch() {
      var vm = this
      const userPK = this.$store.state.loginStore.id
      var rid = this.$store.state.routineStore.routineId
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .patch(
          'http://localhost:8081/routine/' + userPK + '/' + rid,
          {
            date: vm.date,
            routine: vm.content
          },
          config
        )
        .then((res) => {
          alert('수정 완료')
          console.log(res.data)
          document.location = 'http://localhost:8080/myPage'
        })
    }
  }
}
</script>
