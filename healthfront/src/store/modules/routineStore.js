const routineStore = {
  state: {
    routineId: null,
    routineUserId: null
  },
  mutations: {
    setRoutineStore(state, ids) {
      state.routineId = ids.rid
      state.routineUserId = ids.ruid
    }
  },
  getters: {},
  actions: {}
}

export default routineStore
