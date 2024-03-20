package com.noxis.composeexample.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(navController)
    }) {
        it
        BottomBarNavGraph(navController = navController)
    }

}