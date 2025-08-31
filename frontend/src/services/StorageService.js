import axios from 'axios';

// Axios 인스턴스 생성
const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', // 백엔드 API 서버 주소
    headers: {
        'Content-Type': 'application/json'
    }
});

export class StorageService {
    // 전체 항목 조회
    async getItems() {
        const response = await apiClient.get('/memos');
        return response.data;
    }

    // 특정 항목 조회
    async getItem(id) {
        const response = await apiClient.get(`/memos/${id}`);
        return response.data;
    }

    // 신규 항목 삽입 (add)
    async addItem(item) {
        const response = await apiClient.post('/memos', item);
        return response.data;
    }

    // 특정 항목 수정 (set)
    async setItem(item) {
        const response = await apiClient.put(`/memos/${item.id}`, item);
        return response.data;
    }

    // 특정 항목 삭제 (del)
    async delItem(id) {
        await apiClient.delete(`/memos/${id}`);
    }
}