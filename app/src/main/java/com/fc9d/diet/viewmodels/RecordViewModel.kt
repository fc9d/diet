package com.fc9d.diet.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc9d.diet.data.model.Record
import com.fc9d.diet.data.repository.RecordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecordViewModel(private val recordRepository: RecordRepository) : ViewModel() {

    val uiState: StateFlow<RecordUiState> = recordRepository.getList().map {
        RecordUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = RecordUiState()
    )

    private val _isDialogVisible = mutableStateOf(false)
    val isDialogVisible: Boolean
        get() = _isDialogVisible.value

    fun setDialogVisible(visible: Boolean) {
        _isDialogVisible.value = visible
    }

    fun saveRecord(record: Record) {
        viewModelScope.launch {
            recordRepository.insertRecord(record)
        }
    }

    data class RecordUiState(
        val recordList: List<Record> = listOf(),
    )

}