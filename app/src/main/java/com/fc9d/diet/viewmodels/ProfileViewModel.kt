package com.fc9d.diet.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc9d.diet.data.model.Converters
import com.fc9d.diet.data.model.Profile
import com.fc9d.diet.data.repository.ProfileRepository
import com.fc9d.diet.util.hasOneDecimalPlace
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    val uiState: StateFlow<ItemUiState> = profileRepository.getProfile().map {
        if (it != null) {
            ItemUiState(it.fromProfile())
        } else {
            ItemUiState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ItemUiState()
    )
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
    id = 1,
    height = height.toDouble(),
    weight = weight.toDouble(),
    age = age.toInt(),
    gender = Converters().toGender(gender)!!
)

fun Profile.fromProfile(): ItemDetails = ItemDetails(
    height = height.toFormattedString(),
    weight = weight.toFormattedString(),
    age = age.toString(),
    gender = Converters().fromGender(gender)
)

fun Double.toFormattedString(): String {
    return if (this % 1 == 0.0) {
        "%.0f".format(this)
    } else {
        this.toString()
    }
}