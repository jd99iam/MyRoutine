<!-- Header -->
<template>
  <div>
    <!-- nav bar -->
    <nav class="navbar navbar-expand-lg bg-primary" style="padding: 50px">
      <div class="container-fluid">
        <a
          class="navbar-brand"
          href="/myPage"
          style="
            margin-left: 100px;
            font-size: 50px;
            text-shadow: 1px 1px 2px black;
          "
          >MyRoutine</a
        >
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarText"
          aria-controls="navbarText"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link to="/search" style="margin-left: 50px"
                >search</router-link
              >
            </li>
            <li class="nav-item">
              <router-link to="/friends" style="margin-left: 50px"
                >friends</router-link
              >
            </li>
          </ul>
          <!-- dropdown -->
          <span class="navbar-text" style="margin-right: 200px">
            <div class="dropdown">
              <a
                class="btn btn-primary dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                v-if="!this.$store.state.loginStore.isLogin"
              >
                내정보
              </a>
              <a
                class="btn btn-primary dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                v-else
              >
                {{ this.$store.state.loginStore.nickname }}님
              </a>

              <ul class="dropdown-menu">
                <li v-if="this.$store.state.loginStore.isLogin === false">
                  <a class="dropdown-item" href="#"
                    ><router-link to="/login">로그인</router-link></a
                  >
                </li>
                <li v-if="this.$store.state.loginStore.isLogin === false">
                  <a class="dropdown-item" href="#"
                    ><router-link to="/signup">회원가입하기</router-link></a
                  >
                </li>
                <li v-if="this.$store.state.loginStore.isLogin">
                  <a class="dropdown-item" href="/login">정보수정</a>
                </li>
                <li v-if="this.$store.state.loginStore.isLogin">
                  <a class="dropdown-item" href="#" @:click="logout"
                    >로그아웃</a
                  >
                </li>
                <li v-if="this.$store.state.loginStore.isLogin">
                  <a class="dropdown-item" href="#">회원탈퇴</a>
                </li>
              </ul>
            </div>
          </span>
        </div>
      </div>
    </nav>
    <img :src="profileImg" alt="응애" id="image" />
    {{ profileImg }}
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      profileImg: null
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getImg()
  },
  unmounted() {},
  methods: {
    getImg() {
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get('http://localhost:8081/auth/load-profile', config)
        .then((res) => {
          if (res.status === 200) {
            const pl = new Blob([new ArrayBuffer(res)], { type: 'image/png' })
            const url = window.URL.createObjectURL(pl)
            this.profileImg = url
          }
        })
    },
    logout() {
      this.$store.commit('logout')
      document.location = '/'
    }
  }
}
</script>
<style scoped>
.navbar-brand {
  margin-left: 50px;
}
.nav-item {
  margin-right: 50px;
  margin-left: 50px;
}
nav {
  height: 80px;
}
a {
  text-decoration-line: none;
  font-size: 30px;
  color: black;
  text-shadow: 1px 1px 2px black;
}
</style>
