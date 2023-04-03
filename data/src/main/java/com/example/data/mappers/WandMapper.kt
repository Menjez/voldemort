package com.example.data.mappers

import com.example.data.models.WandDomain
import com.example.local.models.CharacterEntity
import com.example.remote.models.CharacterDto

fun CharacterEntity.Wand.toDomain() = WandDomain(
    wood = wood,
    core = core,
    length = length
)
fun CharacterDto.Wand.toDomain() = WandDomain(wood, core, length)

fun WandDomain.toEntity() = CharacterEntity.Wand(wood, core, length)
fun WandDomain.toDto() = CharacterDto.Wand(wood, core, length)