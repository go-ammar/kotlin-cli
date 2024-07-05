package service

import model.News
import retrofit2.http.GET

interface ApiService {

    @GET("everything?q=keyword&pagesize=1&page=1")
    suspend fun getData(): News

}