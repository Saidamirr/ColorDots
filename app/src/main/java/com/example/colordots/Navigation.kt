package com.example.colordots

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SetupNavGraph(navController: NavHostController){

    NavHost(navController = navController,
        startDestination = Screen.Screen1.route) {
        composable(
            route = Screen.Screen1.route
        ) {
            Screen1(navController)
        }
        composable(
            route = Screen.Screen2.route
        ) {
            Screen2(navController)
        }
    }
}