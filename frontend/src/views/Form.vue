<script setup>
import {reactive} from "vue";
import {StorageService} from "@/services/StorageService";
import {useRoute, useRouter} from "vue-router";

// 스토리지 서비스의 객체(인스턴스) 생성
const storageService = new StorageService("myMemo");

// 라우터 객체
const router = useRouter();

// 반응형 상태
const state = reactive({
  memo: {
    id: undefined,
    title: "",
    content: ""
  }
});

const route = useRoute();

// submit 함수 (async/await 추가)
const submit = async () => { // async 추가
  if(route.params.id) {
    await storageService.setItem(state.memo); // await 추가
  }
  else {
    await storageService.addItem(state.memo); // await 추가
  }

  window.alert("저장했습니다.")
  router.push({path: "/"})
};

// onCreated 훅 (async/await 추가)
(async function onCreated() {
  if(route.params.id){
    const id = Number.parseInt(route.params.id.toString());
    state.memo = await storageService.getItem(id); // await 추가
  }
})();
</script>

<template>
  <form class="detail" @submit.prevent="submit"> <!-- ① -->
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <!-- 제목 출력 -->
      <input type="text" id="title" class="form-control p-3" v-model="state.memo.title"/> <!-- ② -->
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용</label>
      <!-- 내용 입력 -->
      <textarea id="content" class="form-control p-3" v-model="state.memo.content"></textarea> <!-- ③ -->
    </div>
    <button type="submit" class="btn btn-primary w-100 py-3">저장</button> <!-- ④ -->
  </form>
</template>