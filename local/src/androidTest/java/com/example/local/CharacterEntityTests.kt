package com.example.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.local.dao.CharacterDao
import com.example.local.database.CharacterDatabase
import com.example.local.models.CharacterEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CharacterEntityTests {

    private lateinit var dao: CharacterDao
    private lateinit var database: CharacterDatabase

    companion object {
        private fun getEntity(id: String, name: String, house: String = "Gryffindor") =
            CharacterEntity(
                id = id,
                name = name,
                alternateNames = listOf(
                    "The Boy Who Lived", "The Chosen One"
                ),
                species = "human",
                gender = "male",
                house = house,
                dateOfBirth = "31-07-1980",
                yearOfBirth = 1980,
                wizard = true,
                ancestry = "half-blood",
                eyeColor = "green",
                hairColor = "black",
                wand = CharacterEntity.Wand(
                    wood = "holly", core = "phoenix feather", length = 11.0
                ),
                patronus = "stag",
                hogwartsStudent = true,
                hogwartsStaff = false,
                actor = "Daniel Radcliffe",
                alternateActors = listOf(),
                alive = true,
                image = "https://ik.imagekit.io/hpapi/harry.jpg"
            )
    }

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CharacterDatabase::class.java).build()
        dao = database.characterDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws
    fun given_CharacterDao_when_getting_Characters_should_return_an_empty_list() =
        runBlocking {
            val character = dao.getCharacters()
            assertTrue(character.isEmpty())
        }

    @Test
    @Throws(Exception::class)
    fun given_CharacterDao_when_saving_a_Character_should_save_Character_successfully() =
        runBlocking {
            val character = getEntity(id = "one", name = "Boom")
            dao.insert(character)
            val characters = dao.getCharacters()
            val first: CharacterEntity = characters.first()
            assertEquals(first, character)
        }

    @Test
    @Throws(IOException::class)
    fun given_CharacterDao_when_deleting_a_Character_should_delete_Character_successfully() =
        runBlocking {
            val character = getEntity(id = "one", name = "james")
            dao.insert(character)
            dao.delete(character)
            val characters = dao.getCharacters()
            assertTrue(characters.isEmpty())
        }

    @Test
    @Throws
    fun given_CharacterDao_when_searching_for_Character_by_name_should_return_correct_characters_list() =
        runBlocking {
            val first = getEntity(id = "one", name = "bryan")
            val second = getEntity(id = "two", name = "james")
            dao.insert(first)
            dao.insert(second)
            val characters = dao.searchCharacters("bryan")
            assertTrue(characters.contains(first))
        }

    @Test
    @Throws
    fun given_CharacterDao_when_searching_for_Character_by_house_should_return_correct_characters_list() =
        runBlocking {
            val first = getEntity(id = "one", name = "bryan", house = "Gryffindor")
            val second = getEntity(id = "two", name = "james", house = "Kenya")
            dao.insert(first)
            dao.insert(second)
            val characters = dao.searchCharacters("Gryffindor")
            assertTrue(characters.size == 1)
        }
}