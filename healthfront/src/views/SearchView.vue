<template>
  <div>
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
  data() {
    return {
      name: null,
      lists: []
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    searchMethod() {
      const name = this.name
      axios
        .get(`http://localhost:8081/auth/showname/${name}`, {
          name: name
        })
        .then((res) => {
          this.lists = res.data
        })
    }
  }
}
</script>
