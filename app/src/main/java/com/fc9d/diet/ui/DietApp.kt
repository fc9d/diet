package com.fc9d.diet.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fc9d.diet.ui.navigation.BottomNavigationBar
import com.fc9d.diet.ui.navigation.Screen
import com.fc9d.diet.ui.chart.ChartScreen
import com.fc9d.diet.ui.history.HistoryScreen
import com.fc9d.diet.ui.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(navController = navController, startDestination = Screen.History.route) {
                composable(Screen.History.route) {
                    HistoryScreen()
                }
                composable(Screen.Profile.route) {
                    ProfileScreen()
                }
                composable(Screen.Chart.route) {
                    ChartScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    DietApp()
}