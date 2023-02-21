<template>
  <div>
    <table class="table table-primary" style="box-shadow: 1px 1px 1px black">
      <thead>
        <tr>
          <td colspan="6">
            <div class="input-group">
              <span class="input-group-text"
                >Search By
                <!-- dropdown -->
                <div
                  class="dropdown"
                  style="display: inline-block; margin-left: 10px"
                >
                  <button
                    class="btn btn-primary dropdown-toggle"
                    type="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    {{ type }}
                  </button>
                  <ul class="dropdown-menu">
                    <li class="dropdown-item" @click="typeToName">이름</li>
                    <li class="dropdown-item" @click="typeToExercise">
                      운동타입
                    </li>
                  </ul>
                </div></span
              >
              <textarea
                class="form-control"
                aria-label="With textarea"
                v-model="name"
              ></textarea>
              <button
                class="btn btn-primary"
                @click="searchMethod"
                style="margin-right: 500px"
              >
                Search
              </button>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="col"></th>
          <th scope="col"><h3>이름</h3></th>
          <th scope="col"><h3>나이</h3></th>
          <th scope="col"><h3>친구여부</h3></th>
          <th scope="col"><h3>팔로워 수</h3></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="lists in lists" v-bind:key="lists">
          <th scope="row"></th>
          <td>{{ lists.name }}</td>
          <td>{{ lists.age }}</td>

          <td>
            <span v-if="lists.id === this.$store.state.loginStore.id">
              <button type="button" class="btn btn-warning" disabled>
                본인입니다
              </button>
            </span>
            <span v-else-if="lists.id !== this.$store.state.loginStore.id">
              <button
                type="button"
                class="btn btn-light"
                v-if="lists2.includes(lists.id)"
                disabled
              >
                이미 친구입니다
              </button>
              <button
                type="button"
                class="btn btn-primary"
                v-else
                @click="plusFriendMethod(lists.id)"
              >
                친구 추가
              </button>
            </span>
          </td>
          <td>{{ lists.follower }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      name: null,
      lists: [],
      type: '이름',
      tokens: this.$store.state.loginStore.token,
      lists2: []
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.show()
  },
  unmounted() {},
  methods: {
    setFollow(idss) {
      const friendid = idss
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get(`http://localhost:8081/friend/countfriends/${friendid}`, config)
        .then((res) => {
          this.follow = res.data
        })
    },
    show() {
      const token = this.tokens
      const config = {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
      axios.get('http://localhost:8081/friend/showid', config).then((res) => {
        this.lists2 = res.data
      })
    },
    plusFriendMethod(id) {
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
          this.message = '추가된 친구입니다.'
          this.lists2.push(friendPK)
        })
    },
    typeToName() {
      this.type = '이름'
      console.log(this.type)
    },
    typeToExercise() {
      this.type = '운동타입'
    },
    searchMethod() {
      console.log('searchMethod')
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
