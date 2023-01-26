import { createStore } from 'vuex'

export default createStore({
  state: {
    authenticated: false,
    userId: null,
    token: null
  },
  getters: {},
  mutations: {
    login(state, resData) {
      state.userId = resData.userId
      state.token = resData.token
      state.authenticated = true
    },
    logout(state) {
      state.userId = null
      state.token = null
      state.authenticated = false
    }
  },
  actions: {},
  modules: {}
})
