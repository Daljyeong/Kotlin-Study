package com.example.week07c.example2

import android.util.Log
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

        // 옵션 인수를 추가하려면 key-value 형태를 사용해줘야 함
        composable(route = Routes.Register.route + "?userid={userID}&passwd={userPasswd}",
            arguments = listOf(
                // 실습
                navArgument(name = "userID") {
                    type = NavType.StringType // String type이라고 타입을 지정해줌
                    defaultValue = "user" // 만약 값이 들어오지 않은 경우
                },
                navArgument(name = "userPasswd") {
                    type = NavType.StringType // String type이라고 타입을 지정해줌
                    nullable = true // null 값을 전달할 수 있게 해줌
                }
            )) {
            // 실습
            Log.d("userid", it.arguments?.getString("userID")!!)
            Register(
                navController = navController,
                userID = it.arguments?.getString("userID"),
                userPasswd = it.arguments?.getString("userPasswd")
            )
        }
    }

}