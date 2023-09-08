package com.fc9d.diet.ui.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fc9d.diet.ui.RecordViewModelProvider
import com.fc9d.diet.ui.theme.AppTheme
import com.fc9d.diet.viewmodels.RecordViewModel

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordViewModel = viewModel(factory = RecordViewModelProvider.Factory),
) {
    val recordUiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn {
            items(items = recordUiState.recordList, key = { it.id }) {
                HistoryItem()
            }
        }
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd), // 오른쪽 하단으로 정렬,
            containerColor = MaterialTheme.colorScheme.primary,
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Filled.Create, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    AppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(100) {
                HistoryItem()
            }
        }
    }

}