package com.fc9d.diet.data.model

import androidx.room.TypeConverter
import java.util.Date

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

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}