package com.fc9d.diet.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(MainUiState("기록"))
    val uiState: StateFlow<MainUiState> = _uiState

    fun updateUiState(mainUiState: MainUiState) {
        _uiState.value = MainUiState(
            mainUiState.screenName
        )
    }

    data class MainUiState(
        val screenName: String,
        val canNavigateBack: Boolean = false
    )
}