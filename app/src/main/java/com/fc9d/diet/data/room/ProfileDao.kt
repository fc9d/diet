package com.fc9d.diet.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fc9d.diet.data.model.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Profile)

    @Update
    suspend fun update(item: Profile)

    @Query("SELECT * from profile LIMIT 1")
    fun getItem(): Flow<Profile?>
}