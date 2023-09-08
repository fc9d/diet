package com.fc9d.diet.ui.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightInputScreen() {
    var weight by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FloatingActionButton(
            onClick = { isDialogVisible = true },
            shape = CircleShape,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }

        if (isDialogVisible) {
            AlertDialog(
                onDismissRequest = {
                    isDialogVisible = false
                },
                title = { Text(text = "Enter Weight") },
                text = {
                    TextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text(text = "Weight") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            // 몸무게를 저장 또는 처리하는 로직을 추가하세요.
                            isDialogVisible = false
                        }
                    ) {
                        Text(text = "Save")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            isDialogVisible = false
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}