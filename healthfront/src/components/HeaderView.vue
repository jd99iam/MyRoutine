<!-- Header -->
<template>
  <div>
    <!-- nav bar -->
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="/myPage" style="margin-left: 200px"
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
              <router-link to="/search">search</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/friends">friends</router-link>
            </li>
          </ul>
          <!-- dropdown -->
          <span class="navbar-text" style="margin-right: 200px">
            <div class="dropdown">
              <a
                class="btn btn-secondary dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                내정보
              </a>

              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="#"
                    ><router-link to="/login">로그인</router-link></a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="#"
                    ><router-link to="/login">정보수정</router-link></a
                  >
                </li>
                <li><a class="dropdown-item" href="#">로그아웃</a></li>
                <li><a class="dropdown-item" href="#">회원탈퇴</a></li>
              </ul>
            </div>
          </span>
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
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
</style>
