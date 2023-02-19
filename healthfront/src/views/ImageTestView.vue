<template>
  <div>
    이미지 업로드 테스트 페이지입니다
    <hr />
    <input multiple @change="imageUpload()" ref="images" type="file" />
    <br/>
    <br/>
    -------------------------------------------------------------------------
    <br/>
    <tr v-for="list in lists" v-bind:key="list">
      <img v-if="this.$store.state.loginStore.token !== null && list !==null" :src="list.profileImg"  alt="profileImg입니다" />
      {{ list }}
    </tr>
    -------------------------------------------------------------------------
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      input: {
        lists: []
      }
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getImg()
  },
  unmounted() {},
  methods: {
    getImg() {
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token,
          ContentType: 'application/json'
        },
        responseType: 'blob' // 이거 있어야함
      }
      axios
        .get('http://localhost:8081/routine/load-profile', config)
        .then((res) => {
          if (res.status === 200) {
            console.log('========================')
            const url = URL.createObjectURL(res.data)
            console.log(url)
            // console.log(url)
            this.lists = res.data
            console.log(this.lists)
            console.log(this.lists[0])
            console.log(this.lists[1])
            console.log(this.lists[2])
          }
        })
    },
    imageUpload() {
      // refs 속성을 이용해 input 태그에 접근함
      this.input.image = this.$refs.images.files
      console.log(this.input.image)
    }
  }
}
</script>
