package com.fc9d.diet.ui.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fc9d.diet.viewmodels.ProfileInfo

@Composable
fun ProfileResultCard(
    profileInfo: ProfileInfo,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = modifier
    ) {

        Text(
            text = "BMI",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                start = 10.dp,
                top = 20.dp
            )
        )
        Text(
            text = "${profileInfo.bmi} : ${classifyBMI(profileInfo.bmi)}",
            style = MaterialTheme.typography.titleSmall,
            modifier = modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}


fun classifyBMI(bmi: Double): String {
    return when {
        bmi < 18.5 -> "저체중"
        bmi < 24.9 -> "정상체중"
        bmi < 29.9 -> "과체중"
        else -> "비만"
    }
}

@Preview
@Composable
fun ProfileResultCardPreview() {
    ProfileResultCard(ProfileInfo())
}

