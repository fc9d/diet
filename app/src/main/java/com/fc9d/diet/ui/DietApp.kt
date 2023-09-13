package com.fc9d.diet.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fc9d.diet.ui.chart.ChartScreen
import com.fc9d.diet.ui.navigation.BottomNavigationBar
import com.fc9d.diet.ui.navigation.Screen
import com.fc9d.diet.ui.profile.ProfileScreen
import com.fc9d.diet.ui.record.HistoryScreen
import com.fc9d.diet.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietApp(
    viewModel: MainViewModel = viewModel(factory = MainViewModelProvider.Factory),
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DietTopAppBar(
                title = uiState.screenName,
                canNavigateBack = uiState.canNavigateBack,
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        floatingActionButton = {}
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.History.route
        ) {
            composable(Screen.History.route) {
                HistoryScreen()
                viewModel.updateUiState(uiState.copy(screenName = "기록"))
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
                viewModel.updateUiState(uiState.copy(screenName = "프로필"))
            }
            composable(Screen.Chart.route) {
                ChartScreen()
                viewModel.updateUiState(uiState.copy(screenName = "차트"))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    DietApp()
}