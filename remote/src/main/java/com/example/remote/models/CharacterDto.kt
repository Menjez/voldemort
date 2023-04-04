package com.example.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("alternate_names") val alternateNames: List<String>,
    @SerialName("species") val species: String,
    @SerialName("gender") val gender: String,
    @SerialName("house") val house: String,
    @SerialName("dateOfBirth") val dateOfBirth: String?,
    @SerialName("yearOfBirth") val yearOfBirth: Int?,
    @SerialName("wizard") val wizard: Boolean,
    @SerialName("ancestry") val ancestry: String,
    @SerialName("eyeColour") val eyeColour: String,
    @SerialName("hairColour") val hairColour: String,
    @SerialName("wand") val wand: Wand,
    @SerialName("patronus") val patronus: String,
    @SerialName("hogwartsStudent") val hogwartsStudent: Boolean,
    @SerialName("hogwartsStaff") val hogwartsStaff: Boolean,
    @SerialName("actor") val actor: String,
    @SerialName("alternate_actors") val alternateActors: List<String>,
    @SerialName("alive") val alive: Boolean,
    @SerialName("image") val image: String
) {
    @Serializable
    data class Wand(
        @SerialName("wood") val wood: String,
        @SerialName("core") val core: String,
        @SerialName("length") val length: Double?
    ) {
        //mapping
    }
    //mapping
}