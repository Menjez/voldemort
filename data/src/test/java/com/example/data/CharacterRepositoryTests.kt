package com.example.data

import com.example.data.repository.CharacterRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterRepositoryTests {

    private val repository : CharacterRepository = FakeRepository()

    @Test
    fun `given CharacterRepository, when fetching characters, should return list of characters`() =
        runBlocking {
            val characters = repository.getCharacters()
            assertTrue(characters.isNotEmpty())
        }

    @Test
    fun `given CharacterRepository, when fetching a character, should return a character`() =
        runBlocking {
            val character = repository.getCharacter("2")
            assertTrue(character.name == "menjez")
        }

    @Test
    fun `given CharacterRepository, when searching for character by name, should return correct character`() =
        runBlocking {
            val characters = repository.searchCharacters("james")
            assertTrue(characters.first().id == "1" && characters.first().house == "dao" )
        }

    @Test
    fun `given CharacterRepository, when searching for character by house, should return correct character`() =
        runBlocking {
            val characters = repository.searchCharacters("ngao")
            assertTrue(characters.first().id == "6" && characters.first().name == "chunju" )

        }
}