<template>
  <!-- 시작페이지 ,header, footer 로그인창 헤더에 router link 있음 -->
  <div>
    <div class="d-flex flex-column min-vh-100">
      <headerView></headerView>
      <router-view
        style="margin-left: 100px; margin-right: 100px"
      ></router-view>
    </div>
    <footerView class="py-5 bg dark mt-auto"></footerView>
  </div>
</template>

<script>
import headerView from '@/components/HeaderView.vue'
import footerView from '@/components/FooterView.vue'

export default {
  components: {
    headerView,
    footerView
  },
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
