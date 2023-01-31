<template>
  <div>
    <div class="dropdown">
      <button
        class="btn btn-secondary dropdown-toggle"
        type="button"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        {{ type }}
      </button>
      <ul class="dropdown-menu">
        <li class="dropdown-item" @click="typeToName">이름</li>
        <li class="dropdown-item" @click="typeToExercise">운동타입</li>
      </ul>
    </div>
    <nav class="navbar navbar-light bg-light">
      <div class="container-fluid">
        <form class="d-flex">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            v-model="name"
          />
          <button
            class="btn btn-outline-success"
            type="submit"
            @click.prevent="searchMethod"
          >
            Search
          </button>
        </form>
      </div>
    </nav>
    <ul>
      <li v-for="lists in lists" v-bind:key="lists">
        이름 : {{ lists.name }} || 나이 : {{ lists.age }}
      </li>
    </ul>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data () {
    return {
      name: null,
      lists: [],
      type: '이름'
    }
  },
  setup () {},
  created () {},
  mounted () {},
  unmounted () {},
  methods: {
    typeToName () {
      this.type = '이름'
      console.log(this.type)
    },
    typeToExercise () {
      this.type = '운동타입'
    },
    searchMethod () {
      const name = this.name
      let type = ''
      if (this.type === '이름') type = 'name'
      else if (this.type === '운동타입') type = 'type'

      axios
        .get(`http://localhost:8081/auth/show${type}/${name}`, {
          name: name
        })
        .then((res) => {
          this.lists = res.data
        })
        .catch((error) => {
          console.log(error)
          alert('해당 회원은 없습니다.')
        })
    }
  }
}
</script>
