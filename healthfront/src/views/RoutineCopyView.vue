<!-- <template>
  <div>
    <label for="exampleInputEmail1" class="form-label">날짜</label>
    <input class="form-control" v-model="date" />
    <label for="exampleInputEmail1" class="form-label">루틴</label>
    <input class="form-control" v-model="content" />
    <button @click="submitCopy" class="btn btn-primary">복사 완료</button>
    <br />
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      date: null,
      content: null
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getRoutine()
  },
  unmounted() {},
  methods: {
    getRoutine() {
      var vm = this
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get(
          'http://localhost:8081/routine/' +
            vm.$store.state.routineStore.routineUserId +
            '/' +
            vm.$store.state.routineStore.routineId,

          config
        )
        .then((res) => {
          vm.date = res.data.date
          vm.content = res.data.routine
        })
    },
    submitCopy() {
      const vm = this
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .post(
          'http://localhost:8081/routine/' + vm.$store.state.loginStore.id,
          {
            date: vm.date,
            routine: vm.content
          },
          config
        )
        .then((res) => {
          console.log(res)
          document.location = 'http://localhost:8080/myPage'
        })
    }
  }
}
</script> -->

<template>
  <div>
    <!---->
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
                  @click="submitCopy"
                  style="margin-left: 480px"
                >
                  복사하기
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
    <!----->
    <!-- <label for="exampleInputEmail1" class="form-label">날짜</label>
    <input class="form-control" type="date" v-model="date" />
    <label for="exampleInputEmail1" class="form-label">루틴</label>
    <input class="form-control" v-model="content" />
    <button @click="submitPatch">수정완료</button>
    <br />
    테스트 <br />
    {{ date }}, {{ content }}, {{ this.$store.state.routineStore.id }} -->
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
      imageUploaded: null,
      image: null
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getRoutine()
    this.getImage()
  },
  unmounted() {},
  methods: {
    getRoutine() {
      const vm = this
      const rid = this.$store.state.routineStore.routineId
      const userPK = this.$store.state.routineStore.routineUserId
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token
        }
      }
      axios
        .get('http://localhost:8081/routine/' + userPK + '/' + rid, config)
        .then((res) => {
          vm.date = res.data.date
          vm.content = res.data.routine
        })
    },
    getImage() {
      const rid = this.$store.state.routineStore.routineId
      var vm = this
      const config = {
        headers: {
          Authorization: 'Bearer ' + this.$store.state.loginStore.token,
          ContentType: 'application/json'
        },
        responseType: 'blob'
      }
      axios
        .get('http://localhost:8081/routine/image/' + rid, config)
        .then((res) => {
          if (res.data != null) {
            vm.image = res.data
            vm.imageUploaded = URL.createObjectURL(res.data)
          }
        })
    },
    upload() {
      this.image = this.$refs.image.files[0] // 사용자가 올린 이미지
      console.log(this.image)
      // URL.createObjectURL로 사용자가 올린 이미지를 URL로 만들어서 화면에 표시할 수 있게 한다. img 태그의 src값에 바인딩해준다
      this.imageUploaded = URL.createObjectURL(this.image)
    },
    submitCopy() {
      // const vm = this
      const userPK = this.$store.state.loginStore.id
      // const rid = this.$store.state.routineStore.routineId
      // const config = {
      //   headers: {
      //     Authorization: 'Bearer ' + this.$store.state.loginStore.token
      //   }
      // }

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
          alert('복사 완료')
          console.log(res.data)
          document.location = 'http://localhost:8080/myPage'
        })
    }
  }
}
</script>
