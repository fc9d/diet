package com.fc9d.diet.data.repository

import com.fc9d.diet.data.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfile(): Flow<Profile?>

    suspend fun insertProfile(item: Profile)

    suspend fun updateProfile(item: Profile)
}