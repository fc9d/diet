package com.fc9d.diet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.fc9d.diet.data.room.AppDatabase
import com.fc9d.diet.ui.DietApp
import com.fc9d.diet.ui.theme.AppTheme
import com.fc9d.diet.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                DietApp()
            }
        }
    }
}

