import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import loginStore from './modules/loginStore'

export default createStore({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { loginStore },
  plugins: [
    createPersistedState({
      paths: ['loginStore']
    })
  ]
})
