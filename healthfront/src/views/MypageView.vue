<template>
  <div>
    여기다 달력에 루틴 넣으면됨
    <div class="row">
      <div class="col-6">test1</div>
      <div class="col-6">test2</div>
    </div>
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
  methods: {
    addOne() {
      this.$store.commit('addOne')
    },
    addParam() {
      this.$store.commit('addParam', 5)
    },
    getAlpha() {
      return this.$store.getters.getAlpha
    }
  }
}
</script>
<style></style>
