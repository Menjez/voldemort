package com.example.local.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ListToStringConverter {
    @TypeConverter
    fun fromListToString(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    fun fromStringToList(string: String): List<String> = Json.decodeFromString(string)
}