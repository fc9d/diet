package com.fc9d.diet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = false)
    val date: String = getDate(),
    val weight: Double,
)

fun getDate(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val currentDate = Date(currentTimeMillis)
    val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    return dateFormat.format(currentDate)
}