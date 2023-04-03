package com.example.data.models


data class WandDomain(
    val wood: String,
    val core: String,
    val length: Int
) {}

data class CharacterDomain(
    val id: String,
    val name: String,
    val alternateNames: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String,
    val yearOfBirth: String,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val wand: WandDomain,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    val alternateActors: List<String>,
    val alive: Boolean,
    val image: String
) {
}