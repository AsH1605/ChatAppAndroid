package com.cookie.chatapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cookie.chatapp.presentation.allRoom.AllRoomScreen
import com.cookie.chatapp.presentation.allRoom.AllRoomVM
import com.cookie.chatapp.presentation.login.LoginVM
import com.cookie.chatapp.presentation.login.UserLoginScreen
import com.cookie.chatapp.presentation.register.UserRegistrationScreen
import com.cookie.chatapp.presentation.register.UserVM
import com.cookie.chatapp.presentation.splash.SplashScreen
import com.cookie.chatapp.presentation.splash.SplashVM

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen"){
        composable(route = "splash_screen") {
            val viewModel = hiltViewModel<SplashVM>()
            SplashScreen(
                viewModel = viewModel,
                navigateToRoomScreen = {
                    navController.navigate("all_room_screen")
                },
                navigateToLoginScreen = {
                    navController.navigate("user_login_screen"){
                        popUpTo("splash_screen"){
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(route = "user_login_screen") {
            val viewModel = hiltViewModel<LoginVM>()
            UserLoginScreen(
                viewModel = viewModel,
                navigateToRegisterScreen = {
                    navController.navigate("user_register_screen"){
                        popUpTo("user_login_screen"){
                            inclusive = true
                        }
                    }
                },
                navigateToRoomScreen = {
                    navController.navigate("all_room_screen"){
                        popUpTo("user_login_screen"){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = "user_register_screen") {
            val viewModel= hiltViewModel<UserVM>()
            UserRegistrationScreen(
                viewModel = viewModel,
                navigateToLoginScreen = {
                    navController.navigate("user_login_screen"){
                        popUpTo("user_register_screen"){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = "all_room_screen") {
            val viewModel = hiltViewModel<AllRoomVM>()
            AllRoomScreen(
                viewModel,
                navigateToLoginScreen = {
                    navController.navigate("user_login_screen"){
                        popUpTo("all_room_screen"){
                            inclusive = true
                        }
                    }
                },
                navigateToRoomScreen = {}
            )
        }
    }
}