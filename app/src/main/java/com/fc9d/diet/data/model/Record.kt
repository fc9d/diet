package com.fc9d.diet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val weight: Double,
)