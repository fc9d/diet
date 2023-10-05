package com.fc9d.diet.ui.record

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fc9d.diet.data.model.Record
import com.fc9d.diet.ui.RecordViewModelProvider
import com.fc9d.diet.ui.theme.AppTheme
import com.fc9d.diet.viewmodels.RecordViewModel

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordViewModel = viewModel(factory = RecordViewModelProvider.Factory),
) {
    val state = viewModel.state.collectAsState().value
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        when (state) {
            is RecordViewModel.RecordState.Error -> EmptyView()
            is RecordViewModel.RecordState.Loading -> LoadingView(
                modifier = Modifier.align(Alignment.Center)
            )
            is RecordViewModel.RecordState.Success -> ListView(items = state.data)
        }

        FloatingActionButton(
            onClick = {
                viewModel.setDialogVisible(true)
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            containerColor = MaterialTheme.colorScheme.primary,
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Filled.Create, contentDescription = null)
        }
        if (viewModel.isDialogVisible) {
            RecordInputScreen(
                onResult = {
                    viewModel.setDialogVisible(false)
                    if (it != null) {
                        viewModel.insertRecord(it)
                    }
                }
            )
        }
    }
}

@Composable
fun ListView(modifier: Modifier = Modifier, items: List<Record>) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        state = listState
    ) {
        items(items = items, key = { it.date }) { HistoryItem(it) }
    }
    LaunchedEffect(items) {
        listState.scrollToItem(0)
    }

}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}

@Composable
fun EmptyView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "아직 기록이 없네요\n기록을 추가해 보세요!",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
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
                HistoryItem(
                    item = Record(
                        weight = 80.0,
                        date = "20230913"
                    )
                )
            }
        }
    }

}