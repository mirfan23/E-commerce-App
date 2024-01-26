package com.example.core.remote.data

sealed class ApiState <out R> private constructor() {
    data class Success<out T>(val data: T) : ApiState<T>()

    data class Error(val errorCode: Int = 500, val error: String = ""): ApiState<Nothing>()

    object Loading: ApiState<Nothing>()
}