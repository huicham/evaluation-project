import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export const useMemoStore = defineStore('memo', () => {
    const memos = ref([]);
    const currentPage = ref(0);
    const totalPages = ref(0);
    const pageSize = 5;

    async function fetchMemos(keyword = '', page = 0) {
        try {
            const params = {
                keyword,
                page,
                size: pageSize,
                sort: 'id,desc'
            };
            if (!keyword) {
                delete params.keyword;
            }
            const response = await apiClient.get('/memos', { params });
            const pageData = response.data;
            memos.value = pageData.content;
            currentPage.value = pageData.number;
            totalPages.value = pageData.totalPages;
        } catch (error) {
            console.error('메모를 불러오는 중 오류 발생:', error);
        }
    }

    async function addMemo(memo) {
        try {
            await apiClient.post('/memos', memo);
            await fetchMemos();
        } catch (error) {
            console.error('메모 추가 중 오류 발생:', error);
        }
    }

    async function updateMemo(memo) {
        try {
            await apiClient.put(`/memos/${memo.id}`, memo);
        } catch (error) {
            console.error('메모 수정 중 오류 발생:', error);
        }
    }

    async function deleteMemo(id) {
        try {
            await apiClient.delete(`/memos/${id}`);
            await fetchMemos('', currentPage.value);
        } catch (error) {
            console.error('메모 삭제 중 오류 발생:', error);
        }
    }

    async function fetchMemoById(id) {
        try {
            const response = await apiClient.get(`/memos/${id}`);
            return response.data;
        } catch (error) {
            console.error('특정 메모를 불러오는 중 오류 발생:', error);
        }
    }

    return { memos, currentPage, totalPages, fetchMemos, addMemo, updateMemo, deleteMemo, fetchMemoById };
});