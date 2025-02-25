package com.cookie.chatapp

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cookie.chatapp.presentation.allRoom.AllRoomScreen
import com.cookie.chatapp.presentation.allRoom.AllRoomVM
import com.cookie.chatapp.presentation.login.LoginVM
import com.cookie.chatapp.presentation.login.UserLoginScreen
import com.cookie.chatapp.presentation.register.UserRegistrationScreen
import com.cookie.chatapp.presentation.register.UserVM
import com.cookie.chatapp.presentation.room.RoomScreen
import com.cookie.chatapp.presentation.room.RoomVM
import com.cookie.chatapp.presentation.splash.SplashScreen
import com.cookie.chatapp.presentation.splash.SplashVM

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen",
        modifier = Modifier.safeDrawingPadding()
    ){
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
                    navController.navigate("user_register_screen")
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
                navigateToRoomScreen = {code->
                    navController.navigate("room_screen?code=$code")
                }
            )
        }

        composable(route = "room_screen?code={code}") {listOf(navArgument(
            name = "code",
            builder = {
                type = NavType.StringType
                nullable = false
            },
            ))
            val viewModel = hiltViewModel<RoomVM>()
            RoomScreen(
                viewModel,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}