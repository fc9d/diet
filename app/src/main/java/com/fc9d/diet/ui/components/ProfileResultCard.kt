package com.fc9d.diet.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileResultCard(modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
    ) {

        Text(
            text = "BMI (체질량 지수)",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 20.dp
            )
        )
        Text(
            text = "29.32 : 과다체중",
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
fun ProfileResultCardPreview() {
    ProfileResultCard()
}