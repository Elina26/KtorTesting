package com.example.ktortesting.android.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktortesting.domain.repository.UniversitiesRepository
import com.example.ktortesting.domain.repository.UniversitiesRepositoryImpl
import com.example.ktortesting.ui.UniversityItemUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val universitiesRepository: UniversitiesRepository = UniversitiesRepositoryImpl()) : ViewModel() {

    data class State(
        val list: List<UniversityItemUI>,
        val isLoading: Boolean = true,
    )

    private val mutableState = MutableStateFlow(State(emptyList()))
    val state = mutableState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        delay(3000)

        mutableState.update {
            it.copy(
                list = universitiesRepository.getUniversities().map { domain ->
                    UniversityItemUI(domain.name, domain.country)
                }
            )
        }

        mutableState.update {
            it.copy(isLoading = false)
        }
    }
}