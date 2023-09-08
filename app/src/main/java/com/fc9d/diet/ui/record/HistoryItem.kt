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

@Composable
fun HistoryItem(
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
            Text(text = "2023.09.07", modifier = Modifier.weight(1f))
            Text(text = "80 kg", modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview() {
    HistoryItem()
}