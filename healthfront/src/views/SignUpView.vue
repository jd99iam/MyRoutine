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
            <th>실명</th>
            <input
              type="text"
              class="form-control"
              name="name"
              v-model="name"
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
              v-model="userId"
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
              v-model="password"
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
              v-model="passwordOk"
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
              v-model="nickname"
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
              v-model="email"
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
              v-model="phone"
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
                v-model="height"
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
                v-model="weight"
                style="margin-left: 20px"
              />
            </tr>
            <br />
            <div>
              <label>성별</label>
              <div>
                <!-- checkbox와 type만 다름 -->
                <input type="radio" value="남자" v-model="gender" />
                <label for="html">남자</label>
              </div>

              <div>
                <input type="radio" value="여자" v-model="gender" />
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
                v-model="age"
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
                  v-model="exerciseType"
                />
                <label for="html">운동타입1</label>
              </div>
              <div>
                <input
                  type="checkbox"
                  value="운동타입2"
                  v-model="exerciseType"
                />
                <label for="css">운동타입2</label>
              </div>
              <div>
                <input
                  type="checkbox"
                  value="운동타입3"
                  v-model="exerciseType"
                />
                <label for="js">운동타입3</label>
              </div>
              <div>선택한 운동타입들: {{ exerciseType }}</div>
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
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    joinMethod() {
      const name = this.name
      const id = this.userId
      const pw = this.password
      const nick = this.nickname
      const email = this.email
      const phone = this.phone
      const height = this.height
      const weight = this.weight
      const gender = this.gender
      const age = this.age
      const exerciseType = this.exerciseType
      axios // axios로 post 요청보냄
        .post('http://localhost:8081/auth/join', {
          name: name,
          userId: id,
          password: pw,
          nickname: nick,
          email: email,
          phone: phone,
          height: height,
          weight: weight,
          gender: gender,
          age: age,
          exerciseType: exerciseType
        })
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
