package com.fc9d.diet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fc9d.diet.ui.ProfileViewModelProvider
import com.fc9d.diet.viewmodels.ProfileInfo
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

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        ProfileDataCard(
            profileInfo = ProfileInfo(

            ),
            onItemValueChange = {},
            modifier = Modifier.padding(top = 20.dp),
        )
        ProfileResultCard(
            profileInfo = ProfileInfo(),
            Modifier.padding(top = 20.dp)
        )

    }
}