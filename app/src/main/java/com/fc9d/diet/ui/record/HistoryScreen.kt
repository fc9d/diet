package com.fc9d.diet.ui.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
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

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Text(
                text = "기록",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            )
        }
        items(items = recordUiState.recordList, key = { it.id }) {
            HistoryItem()
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
            item {
                Text(
                    text = "기록",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 20.dp)
                )
            }
            items(100) {
                HistoryItem()
            }
        }
    }

}