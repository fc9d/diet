package com.fc9d.diet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fc9d.diet.ui.ProfileViewModelProvider
import com.fc9d.diet.viewmodels.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(factory = ProfileViewModelProvider.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = "프로필",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
        ProfileDataCard(
            profileInfo = uiState.profileInfo,
            onItemValueChange = viewModel::updateUiState,
            modifier = Modifier.padding(top = 20.dp),
        )
        if (uiState.profileInfo.bmi > 0) {
            ProfileResultCard(
                profileInfo = uiState.profileInfo,
                Modifier.padding(top = 20.dp)
            )
        }

    }
}