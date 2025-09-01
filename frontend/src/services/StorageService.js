import axios from 'axios';


const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api', 
    headers: {
        'Content-Type': 'application/json'
    }
});

export class StorageService {
    async getItems() {
        const response = await apiClient.get('/memos');
        return response.data;
    }

    
    async getItem(id) {
        const response = await apiClient.get(`/memos/${id}`);
        return response.data;
    }

    
    async addItem(item) {
        const response = await apiClient.post('/memos', item);
        return response.data;
    }

    
    async setItem(item) {
        const response = await apiClient.put(`/memos/${item.id}`, item);
        return response.data;
    }

    
    async delItem(id) {
        await apiClient.delete(`/memos/${id}`);
    }
}