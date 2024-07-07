package model

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data object Loading : DataState<Nothing>()
    data class Error(val exception: Throwable) : DataState<Nothing>()
}