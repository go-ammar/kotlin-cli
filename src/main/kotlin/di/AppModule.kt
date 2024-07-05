package di

import network.RetrofitClient
import org.koin.dsl.module
import repository.NewsRepository

val appModule = module {
    single { RetrofitClient.apiService }
    single { NewsRepository(get()) }
}