package com.fc9d.diet.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc9d.diet.data.model.Profile
import com.fc9d.diet.util.hasOneDecimalPlace

@Composable
fun ProfileDataCard(modifier: Modifier = Modifier, profile: Profile) {
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
            ProfileDataCell(
                "키",
                profile.height,
                { height ->
                    if (height.isEmpty()) profile.height = ""
                    else if (height == "0") profile.height = ""
                    else if (hasOneDecimalPlace(height)) {
                        profile.height = height
                    }
                },
                Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            ProfileDataCell(
                "몸무게",
                profile.weight,
                { weight ->

                    if (weight.isEmpty()) profile.weight = ""
                    else if (weight == "0") profile.weight = ""
                    else if (hasOneDecimalPlace(weight)) {
                        profile.weight = weight
                    }
                },
                Modifier.weight(1f)
            )
        }
        Row(Modifier.padding(horizontal = 10.dp)) {
            ProfileDataCell(
                "나이",
                profile.age,
                { age ->
                    if (age.toIntOrNull() != null) {
                        if (age.toInt() > 0) profile.age = age
                        else profile.age = ""
                    } else if (age.isEmpty()) {
                        profile.age = ""
                    }
                },
                Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            ProfileDataGenderCell(Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDataCardPreview() {
    ProfileDataCard(
        modifier = Modifier,
        Profile()
    )
}