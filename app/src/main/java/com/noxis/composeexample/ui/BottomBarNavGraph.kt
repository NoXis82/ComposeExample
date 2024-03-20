package com.noxis.composeexample.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomBarNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomItem.Screen1.route
    ) {
        composable(route = BottomItem.Screen1.route) {
            Screen1()
        }
        composable(route = BottomItem.Screen2.route) {
            Screen2()
        }
        composable(route = BottomItem.Screen3.route) {
            Screen3()
        }
        composable(route = BottomItem.Screen4.route) {
            Screen4()
        }
    }
}