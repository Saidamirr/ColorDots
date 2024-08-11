package com.example.colordots

sealed class Screen(val route: String) {
    object Screen1 : Screen(route = "screen1")
    object Screen2 : Screen(route = "screen2")
}
