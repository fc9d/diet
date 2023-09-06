package com.fc9d.diet.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.fc9d.diet.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Profile : Screen("profile", R.string.profile, Icons.Filled.Person)
    object History : Screen("history", R.string.history, Icons.Filled.Create)
    object Chart : Screen("chart", R.string.chart, Icons.Filled.Favorite)
}