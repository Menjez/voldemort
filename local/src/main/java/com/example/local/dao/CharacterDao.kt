package com.example.local.dao

import androidx.room.*
import com.example.local.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterTable")
    fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterTable where name Like :query or house LIKE :query ")
    fun searchCharacters(query: String): List<CharacterEntity>

    @Query("SELECT * FROM CharacterTable where id = :id")
    fun getCharacterById(id: String): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: CharacterEntity)

    @Update
    suspend fun update(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

}