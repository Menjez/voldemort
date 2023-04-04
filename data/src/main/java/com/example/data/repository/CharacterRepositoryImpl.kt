package com.example.data.repository

import android.content.Context
import android.util.Log
import com.example.data.models.CharacterDomain
import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.local.database.CharacterDatabase
import com.example.remote.VoldemortApi
import com.example.remote.models.CharacterDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(context: Context) : CharacterRepository {

    private val database = CharacterDatabase.getDatabase(context).characterDao()
    private val api = VoldemortApi.getInstance()

    override suspend fun getCharacters(): List<CharacterDomain> {
        return withContext(Dispatchers.IO) {
            val response = api.getCharacters()
            if (response.isSuccessful.not()) {
                // do something
                Log.i("KosiKosi", "getCharacters: ${response.message}")
                return@withContext emptyList()
            }

            val characters = response.data ?: emptyList<CharacterDto>()
            characters.map { it.toDomain().toEntity() }.forEach { database.insert(it) }
            val mCharacters = database.getCharacters()
            mCharacters.map { it.toDomain() }/*.also {
                for (char in it) Log.i("KosiKosi", "CHAR: $char")
            }*/
        }

    }

    override suspend fun getCharacter(id: String): CharacterDomain {
        val character = database.getCharacterById(id)
        return character.toDomain()
    }

    override suspend fun searchCharacters(query: String): List<CharacterDomain> {
        val list = database.searchCharacters(query)
        return list.map { it.toDomain() }
    }
}