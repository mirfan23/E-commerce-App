package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Category
import com.example.core.domain.model.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilterViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    data class UiState(
        val sortType: SortType = SortType.NONE,
        val category: Set<Category> = setOf(),
        val applyAllFilter: Boolean = false
    ) {
        val filterReviewSortIsChecked = sortType == SortType.REVIEW
        val filterHighestSortIsChecked = sortType == SortType.HIGHEST_PRICE
        val filterLowestSortIsChecked = sortType == SortType.LOWEST_PRICE
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<FilterData>(KEY_FILTER_DATA)?.let { filterData ->
            _uiState.update {
                it.copy(sortType = filterData.sortType, category = filterData.category)
            }
        }
    }

    fun setSortType(sortType: SortType) {
        _uiState.update {
            it.copy(sortType = sortType)
        }
    }

    fun selectCategory(category: Category) {
        _uiState.update {
            val categories = it.category.toMutableSet().apply {
                add(category)
            }
            it.copy(category = categories)
        }
    }

    fun deselectCategory(category: Category) {
        _uiState.update {
            val categories = it.category.toMutableSet().apply {
                remove(category)
            }
            it.copy(category = categories)
        }
    }

    fun applyFilter() {
        _uiState.update {
            it.copy(applyAllFilter = true)
        }
    }
}

const val KEY_FILTER_DATA = "filter_data"

data class FilterData(
    val sortType: SortType = SortType.NONE,
    val category: Set<Category> = setOf(),
) : java.io.Serializable