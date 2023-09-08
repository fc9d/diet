package com.fc9d.diet.data.repository

import com.fc9d.diet.data.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    fun getList(): Flow<List<Record>>

    suspend fun insertRecord(record: Record)

    suspend fun updateRecord(record: Record)
}