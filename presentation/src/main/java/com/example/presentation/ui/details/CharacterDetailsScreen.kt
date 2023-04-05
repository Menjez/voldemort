package com.example.presentation.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CharacterDetailsScreen(viewModel: CharacterViewModel = viewModel(),navController: NavController) {

    val details = viewModel._character.collectAsState().value

    Scaffold {
        Column(modifier = Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally) {
         Text(text = details?.name ?: "oe" )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsPreview(){
   CharacterDetailsScreen(navController = NavController(LocalContext.current))
}

