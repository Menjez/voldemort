package com.example.data

import com.example.data.models.CharacterDomain
import com.example.data.models.WandDomain
import com.example.data.repository.CharacterRepository

class FakeRepository : CharacterRepository {

    private val database = mutableListOf<CharacterDomain>()

    companion object {
        private fun makeCharacter(id: String, name: String, house: String = "Gryffindor") =
            CharacterDomain(
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
                eyeColour = "green",
                hairColour = "black",
                wand = WandDomain(wood = "holly", core = "phoenix feather", length = 11.0),
                patronus = "stag",
                hogwartsStudent = true,
                hogwartsStaff = false,
                actor = "Daniel Radcliffe",
                alternateActors = listOf(),
                alive = true,
                image = "https://ik.imagekit.io/hpapi/harry.jpg"
            )
    }

    init {
        database.addAll(
            listOf(
                makeCharacter(id = "1", name = "james", house = "dao"),
                makeCharacter(id = "2", name = "menjez", house = "mao"),
                makeCharacter(id = "3", name = "mambo", house = "bao"),
                makeCharacter(id = "4", name = "bryan", house = "sao"),
                makeCharacter(id = "5", name = "kahawa", house = "thao"),
                makeCharacter(id = "6", name = "chunju", house = "ngao"),
            )
        )
    }

    override suspend fun getCharacters(): List<CharacterDomain> {
        return database
    }

    override suspend fun getCharacter(id: String): CharacterDomain {
        return database.first { it.id == id }
    }

    override suspend fun searchCharacters(query: String): List<CharacterDomain> {
        return database.filter { it.name.contains(query) or it.house.contains(query) }
    }
}