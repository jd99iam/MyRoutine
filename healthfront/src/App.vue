<template>
  <!-- 시작페이지 ,header, footer 로그인창 헤더에 router link 있음 -->
  <div>
    <div class="d-flex flex-column min-vh-100">
      <MyHeader></MyHeader>
      <router-view
        style="margin-left: 100px; margin-right: 100px"
      ></router-view>
    </div>
    <MyFooter class="py-5 bg dark mt-auto"></MyFooter>
  </div>
</template>

<script>
import MyHeader from '@/components/MyHeader.vue'
import MyFooter from '@/components/MyFooter.vue'

export default {
  components: {
    MyHeader,
    MyFooter
  },
  data () {
    return {
      sampleData: ''
    }
  },
  setup () {},
  created () {},
  mounted () {},
  unmounted () {},
  methods: {}
}
</script>
