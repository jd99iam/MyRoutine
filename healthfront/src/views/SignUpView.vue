<template>
  <div>
    <div class="row">
      <div class="col-4"></div>
      <div
        class="col-4 text-center"
        style="margin-top: 100px; border: 1px solid black; padding: 30px"
      >
        <span style="font-size: 40px">MyRoutine Join</span>
        <hr />
        <form>
          <tr>
            <th>프로필사진</th>
            <input
              @change="upload"
              type="file"
              id="file"
              name="profileImg"
              ref="surveyImage"
              v-bind:src="profileImg"
              class="inputfile"
            />
          </tr>
          <img
            v-if="this.profileImg !== null"
            :src="profileImg"
            alt="프로필 썸네일"
          />
          <span v-else>
            <br />
            <img src="../assets/basic.png" alt="프로필 썸네일" />
            <br />
            기본 이미지입니다
          </span>

          <br />
          <br />
          <tr>
            <th>실명</th>
            <input
              type="text"
              class="form-control"
              name="name"
              v-model="dto.name"
              style="margin-left: 20px"
            />
          </tr>
          <br />
          <tr>
            <th>아이디</th>
            <input
              type="text"
              class="form-control"
              name="userId"
              v-model="dto.userId"
              style="margin-left: 20px"
            />
          </tr>
          <br />
          <tr>
            <th>비밀번호</th>
            <input
              type="password"
              class="form-control"
              name="password"
              v-model="dto.password"
              style="margin-left: 20px"
            />
          </tr>
          <br />
          <tr>
            <th>비밀번호확인</th>
            <input
              type="password"
              class="form-control"
              name="passwordOk"
              v-model="dto.passwordOk"
              style="margin-left: 20px"
            />
          </tr>
          <br />
          <tr>
            <th>닉네임</th>
            <input
              type="text"
              class="form-control"
              name="nickname"
              v-model="dto.nickname"
              style="margin-left: 20px"
            />
          </tr>
          <br />
          <tr>
            <th>이메일</th>
            <input
              type="text"
              class="form-control"
              name="email"
              v-model="dto.email"
              style="margin-left: 20px"
            />
          </tr>
          <br />
          <tr>
            <th>전화번호</th>
            <input
              type="text"
              class="form-control"
              name="phone"
              v-model="dto.phone"
              style="margin-left: 20px"
            />
          </tr>
          <br />

          <div class="collapse" id="collapseExample">
            <tr>
              <th>키</th>
              <input
                type="text"
                class="form-control"
                name="height"
                v-model="dto.height"
                style="margin-left: 20px"
              />
            </tr>
            <br />
            <tr>
              <th>몸무게</th>
              <input
                type="text"
                class="form-control"
                name="weight"
                v-model="dto.weight"
                style="margin-left: 20px"
              />
            </tr>
            <br />
            <div>
              <label>성별</label>
              <div>
                <!-- checkbox와 type만 다름 -->
                <input type="radio" value="남자" v-model="dto.gender" />
                <label for="html">남자</label>
              </div>

              <div>
                <input type="radio" value="여자" v-model="dto.gender" />
                <label for="js">여자</label>
              </div>
            </div>
            <br />
            <tr>
              <th>나이</th>
              <input
                type="text"
                class="form-control"
                name="age"
                v-model="dto.age"
                style="margin-left: 20px"
              />
            </tr>
            <br />
            <br />
            <div>
              <div>
                <label> 운동타입들 </label>
                <br />
                <br />
                <br />
                <input
                  type="checkbox"
                  value="운동타입1"
                  v-model="dto.exerciseType"
                />
                <label for="html">운동타입1</label>
              </div>
              <div>
                <input
                  type="checkbox"
                  value="운동타입2"
                  v-model="dto.exerciseType"
                />
                <label for="css">운동타입2</label>
              </div>
              <div>
                <input
                  type="checkbox"
                  value="운동타입3"
                  v-model="dto.exerciseType"
                />
                <label for="js">운동타입3</label>
              </div>
              <div>선택한 운동타입들: {{ dto.exerciseType }}</div>
            </div>
          </div>
          <br />
          <br />
          <br />

          <button
            style="position: relative; left: 150px"
            class="btn btn-success"
            @click.prevent="joinMethod"
          >
            회원가입하기
          </button>
          <br /><br />
          <p>
            <a
              style="position: relative; left: 150px"
              class="btn btn-primary"
              data-bs-toggle="collapse"
              href="#collapseExample"
              role="button"
              aria-expanded="false"
              aria-controls="collapseExample"
            >
              세부사항까지 기입하기
            </a>
          </p>
        </form>
      </div>
      <div class="col-4"></div>
    </div>
    <br />
  </div>
</template>
<script>
import axios from 'axios'

export default {
  components: {},
  data() {
    return {
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
  mounted() {},
  unmounted() {},
  methods: {
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
