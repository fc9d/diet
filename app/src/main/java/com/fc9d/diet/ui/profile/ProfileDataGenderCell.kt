package com.fc9d.diet.ui.profile

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fc9d.diet.data.model.Gender
import com.fc9d.diet.viewmodels.ItemDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDataGenderCell(
    itemDetails: ItemDetails,
    onValueChange: (ItemDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = itemDetails.gender,
            onValueChange = {},
            label = {
                Text(
                    "성별",
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Gender.values().forEach { gender ->
                DropdownMenuItem(
                    text = { Text(gender.displayName) },
                    onClick = {
                        expanded = false
                        onValueChange(itemDetails.copy(gender = gender.displayName))
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileDataGenderCellPreview() {
    ProfileDataGenderCell(itemDetails = ItemDetails(gender = "남자"), {})
}