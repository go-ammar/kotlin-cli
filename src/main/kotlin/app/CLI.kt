package app

import di.appModule
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import repository.NewsRepository

class CLI : KoinComponent {
    private val newsRepository: NewsRepository by inject()

    init {
        startKoin {
            modules(appModule)
        }
    }

    fun run() = runBlocking {
        coroutineScope {
            while (true) {
                println("Welcome to the Kotlin CLI Application!")
                println("1. Fetch data from API")
                println("2. Exit")
                print("Enter your choice: ")

                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> fetchData()
                    2 -> {
                        println("Exiting...")
                        return@coroutineScope
                    }
                    else -> println("Invalid choice. Try again.")
                }

            }
        }
    }

    private suspend fun fetchData() {
        try {
            val data = newsRepository.fetchData()
            println("Fetched Data: $data")
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
        }
    }
}