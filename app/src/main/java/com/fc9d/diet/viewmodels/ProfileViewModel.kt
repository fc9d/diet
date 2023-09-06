package com.fc9d.diet.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fc9d.diet.data.model.Profile

class ProfileViewModel : ViewModel() {
    private val _profile: MutableState<Profile> = mutableStateOf(getProfile())
    val profile: Profile
        get() = _profile.value
}

private fun getProfile(): Profile = Profile(

)