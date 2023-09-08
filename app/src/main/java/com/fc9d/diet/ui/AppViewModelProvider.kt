/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fc9d.diet.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fc9d.diet.DietApplication
import com.fc9d.diet.viewmodels.ProfileViewModel
import com.fc9d.diet.viewmodels.RecordViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object RecordViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RecordViewModel(dietApplication().container.recordRepository)
        }
    }
}

object ProfileViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProfileViewModel(dietApplication().container.profileRepository)
        }
    }
}

fun CreationExtras.dietApplication(): DietApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as DietApplication)
