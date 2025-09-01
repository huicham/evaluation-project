<script setup>
import { onMounted, ref } from "vue";
import { useMemoStore } from "@/stores/memo";

const memoStore = useMemoStore();
const keyword = ref('');

onMounted(() => {
  memoStore.fetchMemos();
});

const search = () => {
  memoStore.fetchMemos(keyword.value, 0);
};

const changePage = (page) => {
  memoStore.fetchMemos(keyword.value, page - 1);
};

const remove = async (id) => {
  if (!window.confirm('삭제하시겠습니까?')) {
    return;
  }
  await memoStore.deleteMemo(id);
}
</script>

<template>
  <div class="memo-list">
    <div class="search-bar mb-3">
      <form @submit.prevent="search" class="d-flex">
        <input type="text" class="form-control me-2" v-model="keyword" placeholder="검색어를 입력하세요">
        <button type="submit" class="btn btn-primary">검색</button>
      </form>
    </div>

    <router-link :to="`/memos/${m.id}`" class="item" v-for="m in memoStore.memos" :key="m.id">
      <div class="d-flex pt-3">
        <div class="pb-3 mb-0 w-100">
          <div class="d-flex justify-content-between">
            <b>{{ m.title }}</b>
            <div>
              <span role="button" @click.prevent="remove(m.id)">삭제</span>
            </div>
          </div>
          <div class="mt-2">{{ m.content }}</div>
        </div>
      </div>
    </router-link>

    <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4" v-if="memoStore.totalPages > 0">
      <ul class="pagination">
        <li class="page-item" :class="{ disabled: memoStore.currentPage === 0 }">
          <a class="page-link" href="#" @click.prevent="changePage(memoStore.currentPage)">Previous</a>
        </li>
        <li class="page-item" v-for="page in memoStore.totalPages" :key="page" :class="{ active: memoStore.currentPage === page - 1 }">
          <a class="page-link" href="#" @click.prevent="changePage(page)">{{ page }}</a>
        </li>
        <li class="page-item" :class="{ disabled: memoStore.currentPage === memoStore.totalPages - 1 }">
          <a class="page-link" href="#" @click.prevent="changePage(memoStore.currentPage + 2)">Next</a>
        </li>
      </ul>
    </nav>

    <router-link to="/memos/add" class="add btn btn-light mt-3">+ 추가하기</router-link>
  </div>
</template>

<style lang="scss" scoped>
.memo-list {
  .item {
    background: #f8f9fa;
    border: 1px solid #eee;
    display: block;
    color: #000;
    text-decoration: none;
    padding: 20px 30px;
    margin: 15px 0;

    &:hover {
      border-color: #aaa;
    }
  }

  .add {
    display: block;
    padding: 25px;
    border: 1px solid #eee;
  }
}
</style>