package app

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import di.appModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import model.DataState
import model.News
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import repository.NewsRepository
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

class CLI : KoinComponent {
    //injecting the repository using Koin
    private val newsRepository: NewsRepository by inject()

    //Declaring the variable cache for caching of API response
    private var cache: Cache<String, Any>

    init {
        //dependency injection using Koin
        startKoin {
            modules(appModule)
        }

        //initializing cache (using Caffeine library)
        cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES) // Cache entries expire after 10 minutes
            .maximumSize(100) // Maximum of 100 entries in cache
            .build()
    }

    fun run() = runBlocking {
        while (true) {
            //Displaying the options on the Command line interface
            println("Welcome to the Kotlin CLI Application!")
            println("1. Fetch data from API")
            println("2. Exit")
            print("Enter your choice: ")

            //handling user input
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> fetchData()
                2 -> {
                    //exits the application
                    println("Exiting...")
                    exitProcess(0)
                }

                else -> println("Invalid choice. Try again.")
            }

        }
    }

    private suspend fun fetchData() {
        println("If you want a random news article, enter 1. Otherwise, enter your specific desired topic.")
        print("Enter your choice: ")
        val choice = readln()
        val cachedResult = cache.getIfPresent(choice)
        if (cachedResult != null) {
            //if already exists in cache, gets data from cache
            showCachedResponse(cachedResult as News)
        } else {
            when (choice) {
                //fetching data from API
                1.toString() -> fetchDataFromRepository("1")
                else -> fetchDataFromRepository(choice)
            }
        }
    }

    private suspend fun fetchDataFromRepository(topic: String) {
        withContext(Dispatchers.IO) {
            try {
                //getting data from repository and handles different states
                newsRepository.fetchData(topic).collect { state ->
                    when (state) {
                        is DataState.Loading -> println("Loading...") //loading state
                        is DataState.Success -> showData(topic, state.data) //success state
                        is DataState.Error -> println("Error fetching data: ${state.exception.message}") //error state
                    }
                }
            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
            }
        }
    }

    private fun showData(topic: String, news: News) {
        //Shows data fetched from the API
        cache.put(topic, news)
        news.articles.forEach { article ->
            println("Author: ${article.author}")
            println("Title: ${article.title}")
            println("Description: ${article.description}")
            println("Published at: ${article.publishedAt}")
            println("Content: ${article.content}")
            println("Continue reading at : ${article.url}")
            println()
        }
    }

    private fun showCachedResponse(news: News) {
        //Shows data fetched from the cache
        news.articles.forEach { article ->
            println("Author: ${article.author}")
            println("Title: ${article.title}")
            println("Description: ${article.description}")
            println("Published at: ${article.publishedAt}")
            println("Content: ${article.content}")
            println("Continue reading at : ${article.url}")
            println()
        }
    }

}
