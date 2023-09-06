package com.fc9d.diet.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileResultCell(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier
            .padding(10.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileResultCellPreview() {
    ProfileResultCell("키 : 160")
}