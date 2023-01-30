<template>
  <div>
    <div>{{ date }}</div>
    <br />
    <div class="calendar text-center">
      <div>{{ year }}년 {{ month + 1 }}월</div>
      <button @click="nextMonth">다음달</button>
      <button @click="previousMonth">이전달</button> <br />
      막일 : {{ nowMonthEnd }} <br />
      이번달 시작요일 {{ nowMonthStartDay }} <br />
      이번달 시작 Date 객체 {{ nowMonthStartDate }}
      <hr />
      <table class="table table-primary">
        <tr>
          <th>일</th>
          <th>월</th>
          <th>화</th>
          <th>수</th>
          <th>목</th>
          <th>금</th>
          <th>토</th>
        </tr>
        <tr v-for="l in calendarLines" :key="l"></tr>
      </table>
      <br /><br />
    </div>
    <br />
  </div>
</template>
<script>
// import VDatePicker from 'v-calendar/lib/components/date-picker.umd'
export default {
  components: {},
  data() {
    return {
      date: new Date(), // Date 객체 생성 현재 Date
      year: null,
      month: null,
      nowMonthEnd: null, // 현재 월 끝나는 일
      nowMonthStartDay: null, // 현재 월 시작 요일
      nowMonthStartDate: null, // 현재 월 시작 Date 객체
      showDateCount: 1,
      calendarLines: 0,
      dateIdx: 1,
      dates: [] // 이번 달의 dates 2차원배열로 줄을 구분해서 만들자
    }
  },
  setup() {},
  created() {},
  mounted() {
    this.year = this.date.getFullYear() // 현재 년도
    this.month = this.date.getMonth() // 현재 월
    this.nowMonthEnd = new Date(this.year, this.month + 1, 0).getDate()
    this.nowMonthStartDay = new Date(this.year, this.month, 1).getDay()
    this.nowMonthStartDate = new Date(this.year, this.month, 1)
    this.calendarLines = (this.nowMonthEnd + this.nowMonthStartDay) / 7 + 1
  },
  unmounted() {},
  methods: {
    nextMonth() {
      this.month += 1
      if (this.month === 12) {
        this.month = 0
        this.year += 1
      }
      this.nowMonthEnd = new Date(this.year, this.month + 1, 0).getDate()
      this.nowMonthStartDay = new Date(this.year, this.month, 1).getDay()
      this.dates = []
      for (var i = 1; i <= this.nowMonthEnd; i++) {
        this.dates.push(new Date(this.year, this.month, i).getDate())
      }
      this.calendarLines = (this.nowMonthEnd + this.nowMonthStartDay) / 7
      this.dateIdx = 1
    },
    previousMonth() {
      this.month -= 1
      if (this.month === -1) {
        this.month = 11
        this.year -= 1
      }
      this.nowMonthEnd = new Date(this.year, this.month + 1, 0).getDate()
      this.nowMonthStartDay = new Date(this.year, this.month, 1).getDay()
      this.dates = []
      for (var i = 1; i <= this.nowMonthEnd; i++) {
        this.dates.push(new Date(this.year, this.month, i).getDate())
      }
      this.calendarLines = (this.nowMonthEnd + this.nowMonthStartDay) / 7
      this.dateIdx = 1
    },
    makeMatrix() {
      this.dates = []
    }
  }
}
</script>
