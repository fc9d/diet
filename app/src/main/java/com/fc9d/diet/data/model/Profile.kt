package com.fc9d.diet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

enum class Gender(val displayName: String) {
    MALE("남자"), FEMALE("여자")
}

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val height: Double,
    val weight: Double,
    val age: Int,
    val gender: Gender,
)

class Converters {
    @TypeConverter
    fun fromGender(gender: Gender): String {
        return gender.displayName
    }

    @TypeConverter
    fun toGender(displayName: String): Gender? {
        Gender.values().forEach {
            if (it.displayName == displayName) return it
        }
        return null
    }
}