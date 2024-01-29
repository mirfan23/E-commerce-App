package com.example.core.domain.model

sealed class UiState<out R> {
    object  Loading : UiState<Nothing>()
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val error: Throwable): UiState<Nothing>()
    object Empty : UiState<Nothing>()
}