package com.jonrysimbolon.testskillmovie.utils

sealed class ResultStatus<out R> private constructor() {
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val error: String) : ResultStatus<Nothing>()
    object Loading : ResultStatus<Nothing>()
}