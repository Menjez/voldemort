package com.example.local.database.converters

import androidx.room.TypeConverter
import com.example.local.models.CharacterEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WandToStringConverter {
    @TypeConverter
    fun fromWandToString(Wand: CharacterEntity.Wand):String =
        Json.encodeToString(Wand)

    @TypeConverter
    fun fromStringToWand(string: String): CharacterEntity.Wand =
        Json.decodeFromString(string)
}