package com.fc9d.diet.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fc9d.diet.data.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Record)

    @Update
    suspend fun update(item: Record)

    @Query("SELECT * from record ORDER BY date DESC")
    fun getList(): Flow<List<Record>>
}