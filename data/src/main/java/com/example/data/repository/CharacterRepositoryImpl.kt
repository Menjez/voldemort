package com.example.data.repository

import android.content.Context
import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.data.models.CharacterDomain
import com.example.local.database.CharacterDatabase
import com.example.remote.VoldemortApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(context: Context) : CharacterRepository {

    private val database = CharacterDatabase.getDatabase(context).characterDao()
    private val api = VoldemortApi.getInstance()

    override suspend fun getCharacters(): List<CharacterDomain> {
        return withContext(Dispatchers.IO) {
            val response = api.getCharacters()
            if (response.isSuccessful.not()) {
                return@withContext emptyList()
            }

            val characters = response.data ?: emptyList()
            characters.map { it.toDomain().toEntity() }.forEach { database.insert(it) }
            val mCharacters = database.getCharacters()
            mCharacters.map { it.toDomain() }
        }

    }

    override suspend fun getCharacter(id: String): CharacterDomain {
        return withContext(Dispatchers.IO) {
            val character = database.getCharacterById(id)
            character.toDomain()
        }
    }

    override suspend fun searchCharacters(query: String): List<CharacterDomain> {
        return withContext(Dispatchers.IO) {
            database.searchCharacters(query.lowercase())
                .map { it.toDomain() }
        }
    }
}