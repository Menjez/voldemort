package com.example.data.mappers

import com.example.data.models.CharacterDomain
import com.example.local.models.CharacterEntity
import com.example.remote.models.CharacterDto


fun CharacterEntity.toDomain() = CharacterDomain(
    id = id,
    name = name,
    alternateNames = alternateNames,
    species = species,
    gender = gender,
    house = house,
    dateOfBirth = dateOfBirth,
    yearOfBirth = yearOfBirth,
    wizard = wizard,
    ancestry = ancestry,
    eyeColour = eyeColor,
    hairColour = hairColor,
    wand = wand.toDomain(),
    patronus = patronus,
    hogwartsStudent = hogwartsStudent,
    hogwartsStaff = hogwartsStaff,
    actor = actor,
    alternateActors = alternateActors,
    alive = alive,
    image = image
)

fun CharacterDto.toDomain() = CharacterDomain(
    id = id,
    name = name,
    alternateNames = alternateNames,
    species = species,
    gender = gender,
    house = house,
    dateOfBirth = dateOfBirth,
    yearOfBirth = yearOfBirth,
    wizard = wizard,
    ancestry = ancestry,
    eyeColour = eyeColour,
    hairColour = hairColour,
    wand = wand.toDomain(),
    patronus = patronus,
    hogwartsStudent = hogwartsStudent,
    hogwartsStaff = hogwartsStaff,
    actor = actor,
    alternateActors = alternateActors,
    alive = alive,
    image = image
)

fun CharacterDomain.toEntity() = CharacterEntity(
    id = id,
    name = name,
    alternateNames = alternateNames,
    species = species,
    gender = gender,
    house = house,
    dateOfBirth = dateOfBirth,
    yearOfBirth = yearOfBirth,
    wizard = wizard,
    ancestry = ancestry,
    eyeColor = eyeColour,
    hairColor = hairColour,
    wand = wand.toEntity(),
    patronus = patronus,
    hogwartsStudent = hogwartsStudent,
    hogwartsStaff = hogwartsStaff,
    actor = actor,
    alternateActors = alternateActors,
    alive = alive,
    image = image
)

fun CharacterDomain.toDto() = CharacterDto(
    id = id,
    name = name,
    alternateNames = alternateNames,
    species = species,
    gender = gender,
    house = house,
    dateOfBirth = dateOfBirth,
    yearOfBirth = yearOfBirth,
    wizard = wizard,
    ancestry = ancestry,
    eyeColour = eyeColour,
    hairColour = hairColour,
    wand = wand.toDto(),
    patronus = patronus,
    hogwartsStudent = hogwartsStudent,
    hogwartsStaff = hogwartsStaff,
    actor = actor,
    alternateActors = alternateActors,
    alive = alive,
    image = image
)