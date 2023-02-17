<template>
  <div>
    이미지 업로드 테스트 페이지입니다
    <hr />
    <input multiple @change="imageUpload()" ref="images" type="file" />
    {{ image }}
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      input: {
        image: ''
      }
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    imageUpload() {
      // refs 속성을 이용해 input 태그에 접근함
      this.input.image = this.$refs.images.files
      console.log(this.input.image)
    }
  }
}
</script>
