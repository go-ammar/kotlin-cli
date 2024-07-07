package repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import model.DataState
import model.News
import service.ApiService
import utils.Utils.convertDateTime
import utils.Utils.parseError
import utils.Utils.removeEndingCharsOfContent
import utils.Utils.removeHtmlAttributes

class NewsRepository(private val apiService: ApiService) {


    //Method to fetch data from API
    suspend fun fetchData(topic: String): Flow<DataState<News>> {
        return flow {
            //emits loading state
            emit(DataState.Loading)

            val response = if (topic == "1") {
                apiService.getData("everything")
            } else {
                apiService.getData(topic)
            }

            if (response.isSuccessful) {
                //gets the response body
                val news: News? = response.body()
                if (news != null) {

                    //filtering the response by removing unnecessary data
                    news.articles.map {
                        it.content = it.content?.removeEndingCharsOfContent()?.removeHtmlAttributes()
                        it.publishedAt = it.publishedAt?.let { convertDateTime(it) }
                    }

                    //sorting the response with respect to published date
                    news.articles.sortBy { it.publishedAt }

                    //emits success state with data
                    emit(DataState.Success(news))
                } else {
                    //emits error state
                    emit(DataState.Error(Exception("No data available")))
                }
            } else {

                //parsing error using Utils method
                val errorResponse = parseError(response)
                //emits error state with message
                emit(DataState.Error(Exception(errorResponse.message)))
            }
        }.catch { e ->
            emit(DataState.Error(e))
        }.flowOn(Dispatchers.IO)
    }

}