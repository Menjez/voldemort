package com.example.presentation.ui.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.presentation.navigation.Routes


@Composable
fun CharactersScreenContent(
    viewModel: CharactersViewModel = viewModel(),
    navController: NavController
){
    val characters by viewModel.characters.collectAsState()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = characters.size.toString())
        Button(onClick = { navController.navigate(route = Routes.Details.route) }) {
            Text(text = "NEXT")
        }

    }
}


@Preview
@Composable
private fun CharacterScreenPreview(){
    CharactersScreenContent(navController = NavController(LocalContext.current))
}