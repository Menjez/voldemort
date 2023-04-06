package com.example.presentation.ui.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.models.CharacterDomain
import com.example.domain.usecases.GetCharactersUseCase
import com.example.domain.usecases.SearchUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModel(
    app: Application,
) : AndroidViewModel(app) {

    private val useCase: GetCharactersUseCase = GetCharactersUseCase(app)
    private val searchUseCase: SearchUseCase = SearchUseCase(app)

    private val _query = MutableStateFlow("")
    val query get() = _query.asStateFlow()

    private val _uiState = MutableStateFlow(CharactersUIState())
    val uiState get() = _uiState.asStateFlow()

    val characters: Flow<List<CharacterDomain>>
        get() = _query.flatMapLatest {
            val list = when (it.isBlank()) {
                true -> useCase.getCharacters()
                false -> searchUseCase.searchCharacters(it)
            }
            if (list.isEmpty()) _uiState.value = _uiState.value.copy(isLoading = false, error = "Could not get characters")
            else _uiState.value = _uiState.value.copy(isLoading = false, success = true)
            MutableStateFlow(list)
        }

    fun updateQuery(str: String) {
        _query.value = str
    }
}

data class CharactersUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val success: Boolean = false
)