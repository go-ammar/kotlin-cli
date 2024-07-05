package repository

import model.News
import service.ApiService

class NewsRepository(private val apiService: ApiService) {

    suspend fun fetchData(): News {
        return apiService.getData()
    }

}