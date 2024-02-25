package com.example.core.domain.model

data class DataFilter(
    val search: String? = null,
    val sort: String? = null,
    val brand: String? = null,
    val lowest: Int? = null,
    val highest: Int? = null
)
