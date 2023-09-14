package com.fc9d.diet.data.repository

import com.fc9d.diet.data.model.Record
import com.fc9d.diet.data.room.RecordDao
import kotlinx.coroutines.flow.Flow

class OfflineRecordRepository(private val recordDao: RecordDao) : RecordRepository {
    override fun getList(): Flow<List<Record>> = recordDao.getList()
    override fun getListForChart(): Flow<List<Record>> = recordDao.getListForChart()

    override suspend fun insertRecord(record: Record) = recordDao.insert(record)
    override suspend fun updateRecord(record: Record) = recordDao.update(record)
}