package com.fc9d.diet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val weight: Double,
)