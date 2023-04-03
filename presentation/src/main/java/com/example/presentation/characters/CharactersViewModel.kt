package com.example.presentation.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.CharacterDomain
import com.example.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(app: Application) : AndroidViewModel(app) {

    private val useCase = GetCharactersUseCase(app)
    private val _characters = MutableStateFlow<List<CharacterDomain>>(listOf())
    val characters get() = _characters.asStateFlow()
    private val error = MutableStateFlow<String?>(null)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _characters.value = useCase.getCharacters()
            }
            catch (e: Exception){
                error.value = e.message.toString()
            }
        }
    }
}