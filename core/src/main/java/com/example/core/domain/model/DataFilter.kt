package com.example.core.domain.model

data class DataFilter(
    val searchQuery: String? = null,
    val sortType: SortType = SortType.NONE,
    val category: Set<Category> = setOf()
)

enum class SortType {
    NONE,
    REVIEW,
    HIGHEST_PRICE,
    LOWEST_PRICE
}

enum class Category {
    APPLE,
    ASUS,
    DELL,
    LENOVO
}