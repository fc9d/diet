package com.fc9d.diet.ui.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.fc9d.diet.data.model.Record
import com.fc9d.diet.util.hasOneDecimalPlace


@Composable
fun RecordInputScreen(
    modifier: Modifier = Modifier,
    onResult: (Record?) -> Unit,
    record: Record = Record(weight = 0.0),
) {
    var weight by remember { mutableStateOf(if (record.weight > 0) record.weight.toString() else "") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AlertDialog(
            onDismissRequest = { onResult(null) },
            title = { Text(text = record.date.toPrettyDate()) },
            text = {
                TextField(
                    value = weight,
                    onValueChange = {
                        when {
                            it.isEmpty() || it == "0" -> weight = ""
                            hasOneDecimalPlace(it) -> weight = it
                        }
                    },
                    label = { Text(text = "몸무게") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onResult(
                            Record(weight = weight.toDouble(), date = record.date)
                        )
                    },
                    enabled = !(weight.isEmpty() || !hasOneDecimalPlace(weight))
                ) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onResult(null) }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecordInputScreenPreview() {
    RecordInputScreen(
        onResult = {}
    )
}