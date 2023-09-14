package com.fc9d.diet.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc9d.diet.data.repository.RecordRepository
import com.fc9d.diet.ui.record.toChartFloat
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ChartViewModel(repository: RecordRepository) : ViewModel() {

    val uiState: StateFlow<ChartUiState> = repository.getListForChart().map { recordList ->
        val list = recordList.map { record ->
            FloatEntry(record.date.toChartFloat(), record.weight.toFloat())
        }
        Log.i("taehee", "list : $list")
        ChartUiState(entryModelOf(list))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ChartUiState()
    )

//    val data = listOf(
//        "2022-07-01" to 82f,
//        "2022-07-02" to 86f,
//        "2022-07-04" to 84f
//    ).associate { (dateString, yValue) ->
//        LocalDate.parse(dateString) to yValue
//    }
//    return entryModelOf(data.map {
//        FloatEntry(it.key.toEpochDay().toFloat(), it.value)
//    })

    data class ChartUiState(
        val chartList: ChartEntryModel = entryModelOf(emptyList()),
    )
}