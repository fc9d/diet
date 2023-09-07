package com.fc9d.diet.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataCell(
    labelText: String,
    valueText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
) {
    OutlinedTextField(
        value = valueText,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        label = {
            Text(
                text = labelText,
                style = MaterialTheme.typography.bodySmall
            )
        },
        trailingIcon = {
            if (imageVector != null) Icon(
                imageVector,
                null,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileDataCellPreview() {
    ProfileDataCell("키", "", {})
}