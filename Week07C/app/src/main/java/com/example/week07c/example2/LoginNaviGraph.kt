package com.example.week07c.example2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Welcome : Routes("Welcome")
    object Register : Routes("Register")
}

@Composable
fun LoginNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(route = Routes.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Routes.Welcome.route + "/{userID}", // userID가 넘어와야 welcome 루트가 생성됨
            arguments = listOf(
                navArgument(name = "userID") {
                    type = NavType.StringType // String type이라고 타입을 지정해줌
                }
            )
        ) {
            WelcomeScreen(
                navController,
                it.arguments?.getString("userID")
            )
        }

        composable(route = Routes.Register.route + "/{userID}/{userPasswd}", arguments = listOf(
            // 실습
            navArgument(name = "userID") {
                type = NavType.StringType // String type이라고 타입을 지정해줌
            },
            navArgument(name = "userPasswd") {
                type = NavType.StringType // String type이라고 타입을 지정해줌
            }
        )) {
            // 실습
            Register(
                navController = navController,
                userID = it.arguments?.getString("userID"),
                userPasswd = it.arguments?.getString("userPasswd")
            )
        }
    }

}