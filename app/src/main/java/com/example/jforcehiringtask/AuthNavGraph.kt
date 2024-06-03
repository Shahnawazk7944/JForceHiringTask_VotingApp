package com.example.jforcehiringtask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jforcehiringtask.viewModels.MyViewModel

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    myViewModel: MyViewModel
    ) {

    val state by myViewModel.state.collectAsState()
    NavHost(navController = navController, startDestination = Screens.SignIn.route) {

        composable(route = Screens.SignUp.route) {
            SignUp(navController = navController)
        }

        composable(route = Screens.SignIn.route) {
            SignIn(navController = navController)
        }

        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                myViewModel = myViewModel,
                state = state
            )
        }
        composable(route = Screens.AdminScreen.route) {
            AdminScreen(
                navController = navController,
                state = state
            )
        }

    }
}

sealed class Screens(val route: String) {
    data object SignUp : Screens(route = "signUp")
    data object SignIn : Screens(route = "signIn")
    data object HomeScreen : Screens(route = "homeScreen")
    data object AdminScreen : Screens(route = "adminScreen")
}