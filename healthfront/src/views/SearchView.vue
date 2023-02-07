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
    <table class="table">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col">이름</th>
      <th scope="col">나이</th>
      <th scope="col">식별번호</th>
      <th scope="col">친구여부</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="lists in lists" v-bind:key="lists">
      <th scope="row"></th>
      <td>{{ lists.name }}</td>
      <td>{{ lists.age }}</td>
      <td>{{ lists.id }}</td>
      <td>
        <span v-if="lists.id === this.$store.state.loginStore.id">
          <button type="button" class="btn btn-light" disabled>본인입니다</button>
        </span>
        <span v-else-if="lists.id !== this.$store.state.loginStore.id" >
        <button type="button" class="btn btn-light" v-if="lists2.includes(lists.id)" disabled>이미 친구입니다</button>
        <button type="button" class="btn btn-dark" v-else @click="plusFriendMethod(lists.id)">친구 추가</button>
        </span>
      </td>
    </tr>

  </tbody>
</table>
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
      type: '이름',
      tokens: this.$store.state.loginStore.token,
      lists2: []
    }
  },
  setup () {},
  created () {},
  mounted () {
    this.show()
  },
  unmounted () {},
  methods: {
    show () {
      const token = this.tokens
      const config = {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
      axios.get('http://localhost:8081/friend/showid', config)
        .then((res) => {
          this.lists2 = res.data
          console.log(this.lists2)
        })
    },
    plusFriendMethod (id) {
      const friendPK = id
      const token = this.tokens
      const config = {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
      console.log(config)
      axios
        .get(`http://localhost:8081/friend/get/${friendPK}`, config)
        .then((res) => {
          alert('친구가 추가되었습니다.')
          console.log(res)
          this.message = '추가된 친구입니다.'
          this.lists2.push(friendPK)
        })
    },
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
