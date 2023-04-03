package com.example.data.repository

import com.example.data.models.CharacterDomain

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterDomain>

    suspend fun getCharacter(id: String): CharacterDomain

    suspend fun searchCharacters(query: String): List<CharacterDomain>
}