package com.fc9d.diet.ui.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fc9d.diet.ui.ChartViewModelProvider
import com.fc9d.diet.ui.theme.AppTheme
import com.fc9d.diet.viewmodels.ChartViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ChartScreen(
    modifier: Modifier = Modifier,
    viewModel: ChartViewModel = viewModel(factory = ChartViewModelProvider.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (uiState.chartList.entries.isNotEmpty()) {
            val marker = rememberMarker()
            Chart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp),
                chart = lineChart(
                    spacing = 48.dp,
                    axisValuesOverrider = AxisValuesOverrider.fixed(
                        minY = uiState.chartList.entries.flatten().minByOrNull { it.y }?.y
                    )
                ),
                model = uiState.chartList,

                startAxis = rememberStartAxis(
                    itemPlacer = AxisItemPlacer.Vertical.default(
                        maxItemCount = 2
                    )
                ),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = { value, _ ->
                        DateTimeFormatter.ofPattern("MM-dd").format(
                            LocalDate.ofEpochDay(value.toLong()))
                    },
                    itemPlacer = AxisItemPlacer.Horizontal.default(
                        spacing = 3
                    ),
                    guideline = null
                ),
                marker = marker

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
    AppTheme {
        ChartScreen()
    }

}