package com.fc9d.diet.ui.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc9d.diet.util.hasOneDecimalPlace
import com.fc9d.diet.viewmodels.ItemDetails
import com.fc9d.diet.viewmodels.ItemUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataCard(
    itemUiState: ItemUiState,
    onItemValueChange: (ItemDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Text(
            text = "신체 데이터",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 20.dp
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(Modifier.padding(horizontal = 10.dp)) {
            OutlinedTextField(
                value = itemUiState.itemDetails.height,
                onValueChange = {
                    when {
                        it.isEmpty() || it == "0" -> onItemValueChange(
                            itemUiState.itemDetails.copy(height = "")
                        )

                        hasOneDecimalPlace(it) -> onItemValueChange(
                            itemUiState.itemDetails.copy(height = it)
                        )
                    }
                },
                singleLine = true,
                label = {
                    Text(
                        text = "키",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = itemUiState.itemDetails.weight,
                onValueChange = {
                    when {
                        it.isEmpty() || it == "0" -> onItemValueChange(
                            itemUiState.itemDetails.copy(weight = "")
                        )

                        hasOneDecimalPlace(it) -> onItemValueChange(
                            itemUiState.itemDetails.copy(weight = it)
                        )
                    }
                },
                singleLine = true,
                label = {
                    Text(
                        text = "몸무게",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.padding(horizontal = 10.dp)) {
            OutlinedTextField(
                value = itemUiState.itemDetails.age,
                onValueChange = {
                    when {
                        it.isEmpty() || it == "0" -> onItemValueChange(
                            itemUiState.itemDetails.copy(age = "")
                        )

                        else -> it.toIntOrNull()?.let { age ->
                            if (age in 0..200) onItemValueChange(
                                itemUiState.itemDetails.copy(age = it)
                            )
                        }
                    }
                },
                singleLine = true,
                label = {
                    Text(
                        text = "나이",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            ProfileDataGenderCell(
                itemDetails = itemUiState.itemDetails,
                onValueChange = onItemValueChange,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDataCardPreview() {
    ProfileDataCard(
        itemUiState = ItemUiState(),
        onItemValueChange = {}
    )
}