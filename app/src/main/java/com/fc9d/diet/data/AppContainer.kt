package com.fc9d.diet.data

import android.content.Context
import com.fc9d.diet.data.repository.OfflineProfileRepository
import com.fc9d.diet.data.repository.OfflineRecordRepository
import com.fc9d.diet.data.repository.ProfileRepository
import com.fc9d.diet.data.repository.RecordRepository
import com.fc9d.diet.data.room.AppDatabase

interface AppContainer {
    val profileRepository: ProfileRepository
    val recordRepository: RecordRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val profileRepository: ProfileRepository by lazy {
        OfflineProfileRepository(AppDatabase.getDatabase(context).profileDao())
    }
    override val recordRepository: RecordRepository by lazy {
        OfflineRecordRepository(AppDatabase.getDatabase(context).recordDao())
    }
}