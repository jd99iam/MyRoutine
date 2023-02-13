<template>
  <div>
    <div class="mainframe" style="margin : 50px; 60px;60px;0px;">
      <div class="row">
        <div class="col-4">
          <h1 class="display-2" style="text-shadow: 2px 2px 2px gray">
            MyRoutine
          </h1>
        </div>
        <div class="col-8">
          <h1 class="display-6" style="margin-top: 30px">
            운동 루틴 관리/공유
          </h1>
        </div>
      </div>

      <div class="row" style="margin-top: 50px">
        <div class="col">
          <!-- 아코디언 -->
          <div
            class="accordion accordion-flush"
            id="accordionFlushExample"
            style="box-shadow: 2px 2px 3px black; border: 3px solid black"
          >
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingOne">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseOne"
                  aria-expanded="false"
                  aria-controls="flush-collapseOne"
                >
                  <h1 class="display-5" style="text-shadow: 1px 1px 2px black">
                    운동 루틴을 작성하세요!
                  </h1>
                </button>
              </h2>
              <div
                id="flush-collapseOne"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingOne"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body">
                  마이페이지에서 운동 루틴을 작성하세요 <br />날짜를 선택하면
                  해당 앞 뒤 1주의 루틴을 표시합니다
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingTwo">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseTwo"
                  aria-expanded="false"
                  aria-controls="flush-collapseTwo"
                >
                  <h1 class="display-5" style="text-shadow: 1px 1px 2px black">
                    팔로우하세요!
                  </h1>
                </button>
              </h2>
              <div
                id="flush-collapseTwo"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingTwo"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body">
                  원하는 사람을 검색하고 팔로우하세요<br />
                  배우고 싶은 운동 종목을 검색해보세요
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="flush-headingThree">
                <button
                  class="accordion-button collapsed"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target="#flush-collapseThree"
                  aria-expanded="false"
                  aria-controls="flush-collapseThree"
                >
                  <h1 class="display-5" style="text-shadow: 1px 1px 2px black">
                    루틴을 복사하세요!
                  </h1>
                </button>
              </h2>
              <div
                id="flush-collapseThree"
                class="accordion-collapse collapse"
                aria-labelledby="flush-headingThree"
                data-bs-parent="#accordionFlushExample"
              >
                <div class="accordion-body">
                  팔로우 한 사람의 루틴을 복사해보세요<br />
                  원한다면 수정해서 복사할 수 있습니다
                </div>
              </div>
            </div>
          </div>
          <!-- 아코디언 -->
        </div>
        <div class="col">
          <!-- 사진 -->
          <div
            id="carouselExampleFade"
            class="carousel slide carousel-fade"
            style="box-shadow: 2px 2px 3px black; border: 3px solid black"
          >
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img
                  src="@/assets/malwang.png"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
              <div class="carousel-item">
                <img
                  src="@/assets/sungbin.jpg"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
              <div class="carousel-item">
                <img
                  src="@/assets/chulsoon.jpg"
                  class="d-block w-100"
                  alt="..."
                />
              </div>
            </div>
            <button
              class="carousel-control-prev"
              type="button"
              data-bs-target="#carouselExampleFade"
              data-bs-slide="prev"
            >
              <span
                class="carousel-control-prev-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button
              class="carousel-control-next"
              type="button"
              data-bs-target="#carouselExampleFade"
              data-bs-slide="next"
            >
              <span
                class="carousel-control-next-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
          <!-- 사진 -->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  components: {},
  data() {
    return {
      sampleData: ''
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>
<style scoped>
img {
  width: 200px;
  height: 300px;
}
</style>
