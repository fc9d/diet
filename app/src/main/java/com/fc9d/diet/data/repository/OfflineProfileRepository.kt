package com.fc9d.diet.data.repository

import com.fc9d.diet.data.model.Profile
import com.fc9d.diet.data.room.ProfileDao
import kotlinx.coroutines.flow.Flow

class OfflineProfileRepository(private val profileDao: ProfileDao) : ProfileRepository {

    override suspend fun getProfile(): Flow<Profile?> = profileDao.getItem()

    override suspend fun insertProfile(item: Profile) = profileDao.insert(item)

    override suspend fun updateProfile(item: Profile) = profileDao.update(item)
}