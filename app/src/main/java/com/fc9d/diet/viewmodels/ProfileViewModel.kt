package com.fc9d.diet.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fc9d.diet.data.model.Converters
import com.fc9d.diet.data.model.Profile
import com.fc9d.diet.data.repository.ProfileRepository
import com.fc9d.diet.util.hasOneDecimalPlace
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    // 이거는 읽기 전용..
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

    private var _itemUiState = MutableStateFlow(ItemUiState())
    val itemUiState: StateFlow<ItemUiState> = _itemUiState

    init {
        viewModelScope.launch {
            profileRepository.getProfile().collect {
                if (it != null) {
                    _itemUiState.value = ItemUiState(it.fromProfile())
                }
            }
        }
    }


    fun updateUiState(itemDetails: ItemDetails) {
        _itemUiState.value = ItemUiState(
            itemDetails = itemDetails,
            isValid = validateInput(itemDetails)
        )
        Log.i("taehee", "uistate : ${_itemUiState.value.itemDetails}")

        if (_itemUiState.value.isValid) {
            viewModelScope.launch {
                insert()
            }
        }
    }

    suspend fun insert() {
        if (validateInput()) {
            profileRepository.insertProfile(_itemUiState.value.itemDetails.toProfile())
        }
    }

    private fun validateInput(uiState: ItemDetails = _itemUiState.value.itemDetails): Boolean {
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
) {
    val bmi: Double
        get() {
            if (height.toDoubleOrNull() != null && weight.toDoubleOrNull() != null) {
                return calculateBMI(weight.toDouble(), height.toDouble())
            }
            return 0.0
        }
}

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

fun calculateBMI(weightKg: Double, heightCm: Double): Double {
    if (weightKg <= 0.0 || heightCm <= 0.0) {
        return 0.0
    }
    // 센티미터를 미터로 변환
    val heightM = heightCm / 100.0
    val bmi = weightKg / (heightM * heightM)

    // 소수점 두 자리까지 형식화
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(bmi).toDouble()
}
