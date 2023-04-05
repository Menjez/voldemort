package com.example.presentation.navigation

sealed class Routes(val route:String) {
    object Characters:Routes("Characters")
    object Details:Routes("Details")
}