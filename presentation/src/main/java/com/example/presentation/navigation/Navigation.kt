package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.characters.CharactersScreenContent
import com.example.presentation.ui.details.CharacterDetailsScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Characters.route
    ) {
        composable(route = Routes.Characters.route){
            CharactersScreenContent(navController = navController)
        }
        composable(route = Routes.Details.route){
            CharacterDetailsScreen(navController = navController)
        }

    }
}