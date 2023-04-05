package com.example.domain.usecases

import android.content.Context
import com.example.data.repository.CharacterRepository
import com.example.data.repository.CharacterRepositoryImpl

class GetCharacterUseCase(context: Context,private val repository:CharacterRepository = CharacterRepositoryImpl(context)) {

    suspend fun getCharacter(id:String) = repository.getCharacter(id)
}