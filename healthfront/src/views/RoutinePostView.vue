<template>
  <div>
    <div class="row">
      <div class="col">
        <table class="table table-primary">
          <thead>
            <tr>
              <th>날짜</th>
              <td><input type="date" v-model="date" /><br /></td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>루틴</th>

              <td>
                <textarea
                  class="form-control"
                  id="exampleFormControlTextarea1"
                  rows="10"
                  v-model="content"
                ></textarea>
                <br />
                <button
                  class="btn btn-primary"
                  @click="submitPost"
                  style="margin-left: 480px"
                >
                  생성 완료
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col">
        <table class="table table-primary">
          <tbody>
            <tr style="height: 320px">
              <td class="text-center">
                <img :src="imageUploaded" style="height: 300px" />
                <br />
              </td>
            </tr>
            <tr style="height: 20px">
              <td>
                <!-- <label for="formFile" class="form-label">루틴 이미지 선택</label>
                <input class="form-control" type="file" id="formFile"> -->

                <input
                  class="form-control"
                  type="file"
                  ref="image"
                  @change="upload"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
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
      image: null,
      imageUploaded: null
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    upload() {
      this.image = this.$refs.image.files[0] // 사용자가 올린 이미지
      console.log(this.image)
      // URL.createObjectURL로 사용자가 올린 이미지를 URL로 만들어서 화면에 표시할 수 있게 한다. img 태그의 src값에 바인딩해준다
      this.imageUploaded = URL.createObjectURL(this.image)
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
