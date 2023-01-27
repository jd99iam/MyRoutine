const loginStore = {
  state: {
    test: '테스트입니다',
    isLogin: false,
    id: null,
    userId: null,
    nickname: null,
    token: null
  },
  mutations: {
    login(state, data) {
      state.isLogin = true
      state.id = data.id
      state.userId = data.userId
      state.nickname = data.nickname
      state.token = data.token
    },
    logout(state) {
      state.isLogin = false
      state.id = null
      state.userId = null
      state.nickname = null
      state.token = null
    }
  },
  getters: {},
  actions: {}
}

export default loginStore
