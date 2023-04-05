package com.example.domain

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.usecases.GetCharacterUseCase
import com.example.domain.usecases.GetCharactersUseCase
import com.example.domain.usecases.SearchUseCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UseCaseTests {


    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun given_GetCharactersUseCase_when_fetching_characters_list_should_not_be_empty() =
        runBlocking {
            val useCase = GetCharactersUseCase(
                context = instrumentationContext,
                repository = FakeRepository()
            )

            val characters = useCase.getCharacters()
            assertTrue(characters.isNotEmpty())

        }
    @Test
    fun given_GetCharacterUseCase_when_fetching_character_should_return_correct_character() =
        runBlocking {
            val useCase = GetCharacterUseCase(
                context = instrumentationContext,
                repository = FakeRepository()
            )
            val character = useCase.getCharacter("1")
            assertTrue(character.name == "james")
        }
    @Test
    fun given_SearchUseCase_when_searching_for_character_by_name_should_return_correct_character() =
        runBlocking {
            val useCase = SearchUseCase(
                context = instrumentationContext,
                repository = FakeRepository()
            )
            val character = useCase.searchCharacters("james")
            assertTrue(character.first().id == "1")
        }

    @Test
    fun given_SearchUseCase_when_searching_for_character_by_house_should_return_correct_character() =
        runBlocking {
            val useCase = SearchUseCase(
                context = instrumentationContext,
                repository = FakeRepository()
            )
            val character = useCase.searchCharacters("mao")
            assertTrue(character.first().name == "menjez" && character.first().id == "2")
        }

}