<template>
  <div>
    <div class="row">
      <div class="col"></div>
      <div class="col">
        <table
          class="table table-primary text-center"
          style="box-shadow: 0px 4px 4px black"
        >
          <thead>
            <tr>
              <th colspan="2"><h1>Sign Up</h1></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th colspan="2">
                <img
                  v-if="this.profileImg !== null"
                  :src="profileImg"
                  alt="프로필 썸네일"
                  style="width: 250px; height: 250px"
                />
                <span v-else>
                  <br />
                  <img
                    src="../assets/basic.png"
                    alt="프로필 썸네일"
                    style="width: 250px; height: 250px"
                  />
                  <br />
                  기본 이미지입니다
                </span>
              </th>
            </tr>
            <tr>
              <th>프로필 사진</th>
              <th>
                <input
                  @change="upload"
                  type="file"
                  id="file"
                  name="profileImg"
                  ref="surveyImage"
                  v-bind:src="profileImg"
                  class="form-control"
                />
              </th>
            </tr>
            <tr>
              <th>실명</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="name"
                  v-model="dto.name"
                />
              </th>
            </tr>
            <tr>
              <th>ID</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="userId"
                  v-model="dto.userId"
                  @change="checkId"
                /><br />
                <span v-if="idCheck === true" style="color: red"
                  >사용할 수 없는 id입니다</span
                >
              </th>
            </tr>
            <tr>
              <th>비밀번호</th>
              <th>
                <input
                  type="password"
                  class="form-control"
                  name="password"
                  v-model="dto.password"
                />
              </th>
            </tr>
            <tr>
              <th>비밀번호확인</th>
              <th>
                <input
                  type="password"
                  class="form-control"
                  name="passwordOk"
                  v-model="dto.passwordOk"
                  @change="passwordCheck"
                /><br />
                <span v-if="pwCheck === true" style="color: red"
                  >비밀번호가 일치하지 않습니다</span
                >
              </th>
            </tr>
            <tr>
              <th>닉네임</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="nickname"
                  v-model="dto.nickname"
                  @change="checkNickName"
                />
                <br />
                <span v-if="nickCheck === true" style="color: red"
                  >사용할 수 없는 닉네임입니다</span
                >
              </th>
            </tr>
            <tr>
              <th>이메일</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="nickname"
                  v-model="dto.email"
                />
              </th>
            </tr>
            <tr>
              <th>전화번호</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="nickname"
                  v-model="dto.phone"
                />
              </th>
            </tr>
            <tr v-if="show === true">
              <th>키</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="nickname"
                  v-model="dto.height"
                />
              </th>
            </tr>
            <tr v-if="show === true">
              <th>몸무게</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="nickname"
                  v-model="dto.weight"
                />
              </th>
            </tr>
            <tr v-if="show === true">
              <th>성별</th>
              <th>
                <input type="radio" value="남자" v-model="dto.gender" />
                <label for="html">남자</label>
                <input
                  style="margin-left: 50px"
                  type="radio"
                  value="여자"
                  v-model="dto.gender"
                />
                <label for="js">여자</label>
              </th>
            </tr>
            <tr v-if="show === true">
              <th>나이</th>
              <th>
                <input
                  type="text"
                  class="form-control"
                  name="nickname"
                  v-model="dto.age"
                />
              </th>
            </tr>
            <tr v-if="show === true">
              <th>운동타입</th>
              <th>
                <input
                  type="checkbox"
                  value="운동타입1"
                  v-model="dto.exerciseType"
                />
                <label for="html">운동타입1</label>

                <input
                  type="checkbox"
                  value="운동타입2"
                  v-model="dto.exerciseType"
                />
                <label for="css">운동타입2</label>

                <input
                  type="checkbox"
                  value="운동타입3"
                  v-model="dto.exerciseType"
                />
                <label for="js">운동타입3</label>
              </th>
            </tr>
            <tr>
              <th style="width: 40%">
                <button class="btn btn-primary" @click="toggle">
                  세부사항 기입
                </button>
              </th>
              <th>
                <button class="btn btn-danger" @click="joinMethod">
                  회원가입 완료
                </button>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col"></div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  data() {
    return {
      pwCheck: false,
      idCheck: false,
      nickCheck: false,
      users: [],
      show: false,
      profileImg: null,
      dto: {
        name: null,
        userId: null,
        password: null,
        passwordOk: null,
        nickname: null,
        email: null,
        phone: null,
        height: null,
        weight: null,
        gender: null,
        age: null,
        exerciseType: []
      }
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.getUsers()
  },
  unmounted() {},
  methods: {
    passwordCheck() {
      this.pwCheck = false
      if (this.dto.password !== this.dto.passwordOk) {
        this.pwCheck = true
      }
    },
    checkId() {
      this.idCheck = false
      const id = this.dto.userId
      console.log('입력한 id : ' + id)
      this.users.forEach((user) => {
        console.log('비교할 id : ' + user.userId)
        if (id === user.userId) {
          this.idCheck = true
        }
      })
    },
    checkNickName() {
      this.nickCheck = false
      const nick = this.dto.nickname
      this.users.forEach((user) => {
        if (nick === user.nickname) {
          this.nickCheck = true
        }
      })
    },
    getUsers() {
      var vm = this
      axios.get('http://localhost:8081/auth/all').then((res) => {
        vm.users = res.data
      })
    },
    toggle() {
      if (this.show === false) {
        this.show = true
      } else {
        this.show = false
      }
    },
    upload(e) {
      const imageFile = e.target.files
      const url = URL.createObjectURL(imageFile[0]) // [0]을 하는 이유는 데이터가 0 인덱스에 있어서 그럼, createObjectURL에 들어가는 인자는 무조건 blob 객체여야함
      // URL.createObjectURl의 이유는 처음에 올릴 떄는 blob 형태인데 저장은 url 형태로 해야 해서 바꿔서 하는것임
      // 이미지를 화면에 표현하기 위해서는 img 태그의 src 값에 url을 넣어줘야하는데
      // url을 넣어주기 위해 URL.createObjectURL을 사용한 것임
      console.log('ㅋㅋ')
      console.log(url)
      this.profileImg = url
    },
    joinMethod() {
      if (this.idCheck === true || this.nickCheck === true) {
        alert('id 또는 닉네임을 확인해주세요')
        return
      } else if (this.pwCheck === true) {
        alert('비밀번호가 일치하지 않습니다')
        return
      } else if (
        this.dto.name === null ||
        this.dto.userId === null ||
        this.dto.password === null ||
        this.dto.nickname === null ||
        this.dto.email === null ||
        this.dto.phone === null
      ) {
        alert('필수 입력 사항을 입력하지 않았습니다')
        return
      }
      const profileImgs = this.$refs.surveyImage.files[0] // 이미지 바로 가져와서 원래 blob
      const dtos = {
        // dto니까 blob 으로 바꿔줘야함 : 왜냐하면 multipart-form data에 append 해야해서
        // dtos는 application/json type
        name: this.dto.name,
        userId: this.dto.userId,
        password: this.dto.password,
        nickname: this.dto.nickname,
        email: this.dto.email,
        phone: this.dto.phone,
        height: this.dto.height,
        weight: this.dto.weight,
        gender: this.dto.gender,
        age: this.dto.age,
        exerciseType: this.dto.exerciseType
      }
      const userFormData = new FormData()
      const dto = new Blob([JSON.stringify(dtos)], { type: 'application/json' }) // dtos를 blob 객체화 시켜줌
      userFormData.append('profileImg', profileImgs)
      userFormData.append('dto', dto)
      const config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }

      axios // axios로 post 요청보냄
        .post('http://localhost:8081/auth/join', userFormData, config)
        .then((res) => {
          if (res.status === 200) {
            console.log(res)
            alert('회원가입이 정상적으로 이루어졌습니다.')
            location.href = '/'
          } else {
            alert('회원가입이 정상적으로 이루어지지 않았습니다.')
            console.log('응답코드 200 아님')
          }
        })
    }
  }
}
</script>
