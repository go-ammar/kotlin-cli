package network

import io.github.cdimascio.dotenv.dotenv
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.ApiService
import utils.Config

object RetrofitClient {

    private val dotenv = dotenv()
    private val apiKey = dotenv["API_KEY"]

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("X-Api-Key", apiKey)
                .method(originalRequest.method, originalRequest.body)
                .build()
            chain.proceed(newRequest)
        })
    }.build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}