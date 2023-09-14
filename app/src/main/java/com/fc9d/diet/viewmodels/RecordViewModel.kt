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

    val state: StateFlow<RecordState> = recordRepository.getList().map {
        if (it.isEmpty()) {
            RecordState.Error
        } else {
            RecordState.Success(it)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = RecordState.Loading
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
    sealed class RecordState {
        object Loading : RecordState()
        data class Success(val data: List<Record>) : RecordState()
        object Error : RecordState()
    }

}