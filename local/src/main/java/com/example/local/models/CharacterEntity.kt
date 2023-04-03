package com.example.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "CharacterTable")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "alternate_names") val alternateNames: List<String>,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "house") val house: String,
    @ColumnInfo(name = "dateOfBirth") val dateOfBirth: String,
    @ColumnInfo(name = "yearOfBirth") val yearOfBirth: String,
    @ColumnInfo(name = "wizard") val wizard: Boolean,
    @ColumnInfo(name = "ancestry") val ancestry: String,
    @ColumnInfo(name = "eyeColor") val eyeColor: String,
    @ColumnInfo(name = "hairColor") val hairColor: String,
    @ColumnInfo(name = "wand") val wand: Wand,
    @ColumnInfo(name = "patronus")val patronus:String,
    @ColumnInfo(name = "hogwartsStudent") val hogwartsStudent: Boolean,
    @ColumnInfo(name = "hogwartsStaff") val hogwartsStaff: Boolean,
    @ColumnInfo(name = "actor") val actor: String,
    @ColumnInfo(name = "alternate_actors") val alternateActors: List<String>,
    @ColumnInfo(name = "alive") val alive: Boolean,
    @ColumnInfo(name = "image")val image:String
){
    @Serializable
    data class Wand(
        val wood:String,
        val core:String,
        val length:Int
    )
}