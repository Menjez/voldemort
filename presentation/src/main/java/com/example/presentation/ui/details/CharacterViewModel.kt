package com.example.presentation.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.models.CharacterDomain
import com.example.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(app:Application,savedStateHandle: SavedStateHandle):AndroidViewModel(app) {

    private val id = savedStateHandle.get<String>("id")
    private val useCase = GetCharacterUseCase(app)

    private val mCharacter = MutableStateFlow<CharacterDomain?>(null)
    val _character = mCharacter.asStateFlow()

    private val error = MutableStateFlow<String?>(null)

    init {
        getCharacter(id = id?:"")
    }

    private fun getCharacter(id:String){
        viewModelScope.launch {
            try {
                val character = useCase.getCharacter(id = id)
                mCharacter.value = character
            }catch(e:Exception) {
                error.value = e.localizedMessage
            }
        }
    }
}