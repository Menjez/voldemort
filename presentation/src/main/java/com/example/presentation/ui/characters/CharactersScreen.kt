package com.example.presentation.ui.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.data.models.CharacterDomain
import com.example.presentation.navigation.Routes


@Composable
fun CharactersScreenContent(
    viewModel: CharactersViewModel = viewModel(),
    navController: NavController
) {
    val characters by viewModel.characters.collectAsState()
    var character by remember{
        mutableStateOf<CharacterDomain?>(null)
    }


    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(items = characters) {
                SpeakerCard(navController = navController, character = it)
            }
        }
    }
}

@Composable
fun SpeakerCard(navController: NavController,character:CharacterDomain){

    Card() {
        Column() {
            Text(text = character.name )
            Text(text = character.actor )
            Button(onClick = { navController.navigate(Routes.Details.route.plus("?id=").plus(character.id)) }) {
                Text(text = "Next")
            }
        }
    }
}


@Preview
@Composable
private fun CharacterScreenPreview() {
    CharactersScreenContent(navController = NavController(LocalContext.current))
}