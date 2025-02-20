package com.cookie.chatapp.presentation.splash

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.cookie.chatapp.R
import com.cookie.chatapp.presentation.splash.model.VmEvent

@Composable
fun SplashScreen(
    viewModel: SplashVM,
    navigateToRoomScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.vmEvent.collect(collector = {event->
            when(event){
                VmEvent.NavigateToChatScreen -> navigateToRoomScreen()
                VmEvent.NavigateToLoginScreen -> navigateToLoginScreen()
                null -> {}
            }
        })
    }
}

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(256.dp).clip(CircleShape),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Splash Screen"
        )
        Image(
            modifier = Modifier.size(256.dp),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Splash Screen"
        )
    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen()
}