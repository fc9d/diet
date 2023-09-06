package com.fc9d.diet.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

enum class Gender(val displayName: String) {
    MALE("남자"), FEMALE("여자")
}

class Profile {
    var gender: Gender by mutableStateOf(Gender.MALE)
    var weight: String by mutableStateOf("")
    var age: String by mutableStateOf("")
    var height: String by mutableStateOf("")

    val bmi: Double
        get() {
            if (weight.isNotEmpty() && weight.toDoubleOrNull() != null && height.isNotEmpty() && height.toDoubleOrNull() != null) {
                return weight.toDouble() / (height.toDouble() * height.toDouble())
            }
            return 0.0
        }
}