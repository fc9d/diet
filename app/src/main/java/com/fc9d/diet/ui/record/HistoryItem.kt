package com.fc9d.diet.ui.record

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc9d.diet.data.model.Record

@Composable
fun HistoryItem(
    item: Record,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(text = item.date.toPrettyDate(), modifier = Modifier.weight(1f))
            Text(text = item.weight.toString() + " KG", modifier = Modifier.weight(1f))
        }
    }
}

fun String.toPrettyDate(): String {
    val year = substring(0, 4)
    val month = substring(4, 6)
    val day = substring(6, 8)
    return "$year-$month-$day"
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview() {
    HistoryItem(
        item = Record(
            weight = 80.0,
            date = "20230913"
        )
    )
}