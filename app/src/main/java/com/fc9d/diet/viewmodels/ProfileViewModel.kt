package com.fc9d.diet.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fc9d.diet.data.model.Converters
import com.fc9d.diet.data.model.Profile
import com.fc9d.diet.data.repository.ProfileRepository
import com.fc9d.diet.util.hasOneDecimalPlace

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(
            itemDetails = itemDetails,
            isValid = validateInput(itemDetails)
        )
        Log.i("taehee", "uistate : ${itemUiState.itemDetails}")
    }

    suspend fun insert() {
        if (validateInput()) {
            profileRepository.insertProfile(itemUiState.itemDetails.toProfile())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            if (age.toIntOrNull() == null || age.toIntOrNull()!! <= 0) return false
            if (weight.isEmpty() || !hasOneDecimalPlace(weight)) return false
            if (height.isEmpty() || !hasOneDecimalPlace(height)) return false
            if (Converters().toGender(gender) == null) return false
            true
        }
    }
}

data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isValid: Boolean = false,
)

data class ItemDetails(
    val height: String = "",
    val weight: String = "",
    val age: String = "",
    val gender: String = "",
)

fun ItemDetails.toProfile(): Profile = Profile(
    height = height.toDouble(),
    weight = weight.toDouble(),
    age = age.toInt(),
    gender = Converters().toGender(gender)!!
)