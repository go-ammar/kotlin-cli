package service

import model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Retrofit GET request to fetch news articles
    //q is the topic. pageSize and page is fixed.
    @GET("everything")
    suspend fun getData(
        @Query("q") topic: String,
        @Query("pagesize") pageSize: Int = 10,
        @Query("page") page: Int = 1
    ): Response<News>

}