package com.fc9d.diet.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.fc9d.diet.viewmodels.ProfileInfo
import com.fc9d.diet.viewmodels.ProfileUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataCard(
    profileInfo: ProfileInfo,
    onItemValueChange: (ProfileInfo) -> Unit,
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

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = profileInfo.height,
                onValueChange = {
                    when {
                        it.isEmpty() || it == "0" -> onItemValueChange(
                            profileInfo.copy(height = "")
                        )

                        hasOneDecimalPlace(it) -> onItemValueChange(
                            profileInfo.copy(height = it)
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
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp)
            )
            OutlinedTextField(
                value = profileInfo.weight,
                onValueChange = {
                    when {
                        it.isEmpty() || it == "0" -> onItemValueChange(
                            profileInfo.copy(weight = "")
                        )

                        hasOneDecimalPlace(it) -> onItemValueChange(
                            profileInfo.copy(weight = it)
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
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
            )
        }
        Row(
            Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 20.dp)) {
            OutlinedTextField(
                value = profileInfo.age,
                onValueChange = {
                    when {
                        it.isEmpty() || it == "0" -> onItemValueChange(
                            profileInfo.copy(age = "")
                        )

                        else -> it.toIntOrNull()?.let { age ->
                            if (age in 0..200) onItemValueChange(
                                profileInfo.copy(age = it)
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
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp)
            )
            ProfileDataGenderCell(
                profileInfo = profileInfo,
                onValueChange = onItemValueChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDataCardPreview() {
    ProfileDataCard(
        profileInfo = ProfileUiState().profileInfo,
        onItemValueChange = {}
    )
}