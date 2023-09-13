package com.fc9d.diet.ui.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordInputScreen(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    onSuccess: (Record) -> Unit,
) {
    var weight by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AlertDialog(
            onDismissRequest = onClose,
            title = { Text(text = "몸무게를 입력해 주세요.") },
            text = {
                TextField(
                    value = weight,
                    onValueChange = {
                        when {
                            it.isEmpty() || it == "0" -> weight = ""
                            hasOneDecimalPlace(it) -> weight = it
                        }
                    },
                    label = { Text(text = "Weight") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSuccess(
                            Record(
                                weight = weight.toDouble(),
                                date = getDate()
                            )
                        )
                    },
                    enabled = !(weight.isEmpty() || !hasOneDecimalPlace(weight))
                ) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(
                    onClick = onClose
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

fun getDate(): String {
    // 현재 시간을 가져옵니다.
    val currentTimeMillis = System.currentTimeMillis()

    // Date 객체를 생성하여 현재 시간을 설정합니다.
    val currentDate = Date(currentTimeMillis)

    // 원하는 날짜 형식으로 포맷을 지정합니다.
    val dateFormat = SimpleDateFormat("yyyyMMdd", java.util.Locale.getDefault())

    // 포맷에 따라 날짜를 문자열로 변환합니다.
    return dateFormat.format(currentDate)
}

@Preview(showBackground = true)
@Composable
fun RecordInputScreenPreview() {
    RecordInputScreen(
        onClose = {},
        onSuccess = {}
    )
}