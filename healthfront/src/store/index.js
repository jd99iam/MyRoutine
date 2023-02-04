import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import loginStore from './modules/loginStore'
import routineStore from './modules/routineStore'

export default createStore({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: { loginStore, routineStore },
  plugins: [
    createPersistedState({
      paths: ['loginStore', 'routineStore']
    })
  ]
})
