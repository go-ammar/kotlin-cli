package repository

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import model.Articles
import model.DataState
import model.News
import model.Source
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.BeforeEach
import retrofit2.Response
import service.ApiService
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryTest {

    // Declare the variables for testing
    private lateinit var newsRepository: NewsRepository

    // Mocking the ApiService
    private val apiService: ApiService = mockk()

    // Method that's called before each test to set up the environment
    @BeforeEach
    fun setUp() {
        newsRepository = NewsRepository(apiService)
    }

    @Test
    fun `fetchData should emit Loading and then Success when api call is successful`() = runTest {
        //Mock response for success
        val mockNews = News(
            status = "ok",
            totalResults = 1,
            articles = arrayListOf(
                Articles(
                    source = Source(id = "1", name = "Mock Source"),
                    author = "Mock Author",
                    title = "Mock Title",
                    description = "Mock Description",
                    url = "https://mock.url",
                    urlToImage = "https://mock.image.url",
                    publishedAt = "2024-06-16T21:30:40Z",
                    content = "Mock Content [+16273 chars]"
                )
            )
        )
        // Mock the service to return the mock response
        coEvery { apiService.getData(any()) } returns Response.success(mockNews)

        val result = mutableListOf<DataState<News>>()
        // Collect the emitted states from the fetchData method
        newsRepository.fetchData("topic").collect {
            result.add(it)
        }

        // Asserts that the first state is Loading and the second state is Success
        assertTrue(result[0] is DataState.Loading)
        assertTrue(result[1] is DataState.Success)
    }

    @Test
    fun `fetchData should emit Loading and then Error when api call fails`() = runTest {

        // Mock the API service to return an error response
        coEvery { apiService.getData(any()) } returns Response.error(
            400,
            "Bad Request".toResponseBody("application/json".toMediaTypeOrNull())
        )

        val result = mutableListOf<DataState<News>>()
        // Collect the emitted states from the fetchData method
        newsRepository.fetchData("topic").collect {
            result.add(it)
        }

        // Asserts that the first state is Loading and the second state is Error
        assertTrue(result[0] is DataState.Loading)
        assertTrue(result[1] is DataState.Error)
    }
}