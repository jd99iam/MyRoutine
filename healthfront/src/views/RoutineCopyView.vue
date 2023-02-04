<template>
  <div>
    <label for="exampleInputEmail1" class="form-label">날짜</label>
    <input class="form-control" v-model="date" />
    <label for="exampleInputEmail1" class="form-label">루틴</label>
    <input class="form-control" v-model="content" />
    <button @click="submitCopy" class="btn btn-primary">복사 완료</button>
    <br />
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
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get(
          'http://localhost:8081/routine/' +
            vm.$store.state.routineStore.routineUserId +
            '/' +
            vm.$store.state.routineStore.routineId,

          config
        )
        .then((res) => {
          vm.date = res.data.date
          vm.content = res.data.routine
        })
    },
    submitCopy() {
      const vm = this
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .post(
          'http://localhost:8081/routine/' + vm.$store.state.loginStore.id,
          {
            date: vm.date,
            routine: vm.content
          },
          config
        )
        .then((res) => {
          console.log(res)
          document.location = 'http://localhost:8080/myPage'
        })
    }
  }
}
</script>
