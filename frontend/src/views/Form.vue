<script setup>
import { reactive, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useMemoStore } from "@/stores/memo";

const memoStore = useMemoStore();
const router = useRouter();
const route = useRoute();

const state = reactive({
  memo: {
    id: undefined,
    title: "",
    content: ""
  }
});

const submit = async () => {
  if (route.params.id) {
    await memoStore.updateMemo(state.memo);
  } else {
    await memoStore.addMemo(state.memo);
  }

  window.alert("저장했습니다.");
  router.push({ path: "/" });
};

onMounted(async () => {
  if (route.params.id) {
    const id = Number.parseInt(route.params.id.toString());
    state.memo = await memoStore.fetchMemoById(id);
  }
});
</script>

<template>
  <form class="detail" @submit.prevent="submit"> 
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input type="text" id="title" class="form-control p-3" v-model="state.memo.title"/> 
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용</label>
      <textarea id="content" class="form-control p-3" v-model="state.memo.content"></textarea> 
    </div>
    <button type="submit" class="btn btn-primary w-100 py-3">저장</button> 
  </form>
</template>