<template>
  <div>
    <label for="exampleInputEmail1" class="form-label">날짜</label>
    <input class="form-control" v-model="date" />
    <label for="exampleInputEmail1" class="form-label">루틴</label>
    <input class="form-control" v-model="content" />
    <label for="exampleInputEmail1" class="form-label">이미지</label>
    <input multiple type="file" ref="image" @change="upload" />
    <img v-for="(image, i) in imageUploaded" :key="i" :src="image" />
    <!-- <img :src="imageUploaded" alt="올린 이미지" /> <br /> -->

    <br />
    <button @click="submitPost">생성 완료</button>
    <br />
    테스트 <br />
    {{ date }}, {{ content }}, {{ this.$store.state.routineStore.id }}
  </div>
</template>
<script>
import axios from 'axios'

export default {
  components: {},
  data() {
    return {
      date: null,
      content: null,
      image: [],
      imageUploaded: []
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    upload() {
      // 수정 전
      // this.image = this.$refs.image.files[0] // 사용자가 올린 이미지
      // console.log(this.image)
      // // URL.createObjectURL로 사용자가 올린 이미지를 URL로 만들어서 화면에 표시할 수 있게 한다. img 태그의 src값에 바인딩해준다
      // this.imageUploaded = URL.createObjectURL(this.image)

      const imageArray = this.$refs.image.files
      for (var i = 0; i < imageArray.length; i++) {
        this.image.push(imageArray[i])
        this.imageUploaded.push(URL.createObjectURL(this.image[i]))
        console.log(this.image[i])
      }
      console.log(this.image)
    },
    submitPost() {
      // 원래 있던 dto랑 이미지를 UserFormData에 넣어서 axios로 보내야함

      // const vm = this
      const userPK = this.$store.state.loginStore.id

      const dto = {
        date: this.date,
        routine: this.content
      }
      // 먼저 dto를 blob으로 바꿈
      const dtoToBlob = new Blob([JSON.stringify(dto)], {
        type: 'application/json'
      })

      // FormData를 만듬
      var formData = new FormData()

      // blob으로 바꾼 dto랑 사용자가 입력한 이미지 formData에 append함
      formData.append('routineDTO', dtoToBlob)
      formData.append('image', this.image)

      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token,
          'Content-Type': 'multipart/form-data' // 컨텐츠 타입 지정해줘야함
        }
      }
      axios
        .post('http://localhost:8081/routine/' + userPK, formData, config)
        .then((res) => {
          alert('생성 완료')
          console.log(res.data)
          document.location = 'http://localhost:8080/myPage'
        })
    }
  }
}
</script>
