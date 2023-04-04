package com.example.presentation.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CharactersScreenContent(
    viewModel: CharactersViewModel = viewModel(),
){
    val characters by viewModel.characters.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = characters.size.toString())
    }
}

/*
@Composable
fun CharactersScreen(){
    CharactersScreenContent(viewModel = CharactersViewModel(app = Application()))
}*/

@Preview
@Composable
private fun CharacterScreenPreview(){
    CharactersScreenContent()
}