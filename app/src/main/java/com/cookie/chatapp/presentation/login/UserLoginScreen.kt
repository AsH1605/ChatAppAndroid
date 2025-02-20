package com.cookie.chatapp.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookie.chatapp.R
import com.cookie.chatapp.presentation.login.model.UiEvent
import com.cookie.chatapp.presentation.login.model.UiState
import com.cookie.chatapp.presentation.login.model.VmEvent
import com.cookie.chatapp.presentation.theme.ChatAppTheme

@Composable
fun UserLoginScreen(
    viewModel: LoginVM,
    navigateToRegisterScreen: () -> Unit,
    navigateToRoomScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.vmEvent.collect(collector = {event->
            when(event){
                VmEvent.NavigateToRegisterScreen -> navigateToRegisterScreen()
                VmEvent.NavigateToRoomScreen -> navigateToRoomScreen()
            }
        })
    }
    UserLoginScreen(
        uiState = uiState,
        onUiEvent = {event->
            viewModel.onUiEvent(event)
        }
    )
}

@Composable
private fun UserLoginScreen(
    uiState: UiState,
    onUiEvent: (UiEvent) -> Unit
) {
    if (uiState.error != null){
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(uiState.error)
            },
            confirmButton = {
                Button(onClick = {onUiEvent(UiEvent.OnDismissError)}) {
                    Text(
                        text = stringResource(R.string.dismiss)
                    )
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = uiState.username,
            onValueChange = {updatedUsername->
                onUiEvent(UiEvent.OnUsernameChange(updatedUsername))
            },
            placeholder = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = {updatedPassword->
                onUiEvent(UiEvent.OnPasswordChange(updatedPassword))
            },
            placeholder = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                onUiEvent(UiEvent.OnLoginClicked)
            }
        ) {
            Text(
                "Login!",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                onUiEvent(UiEvent.OnRegisterClicked)
            }
        ) {
            Text(
                "New User? Signup now!",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun PreviewUserRegistrationScreen() {
    ChatAppTheme {
        UserLoginScreen(
            uiState = UiState(
                username = "Username",
                password = "Password",
                error = null
            ),
            onUiEvent = {}
        )
    }

}

@Preview
@Composable
private fun PreviewUserRegistrationScreen2() {
    ChatAppTheme(darkTheme = true) {
        UserLoginScreen(
            uiState = UiState(
                username = "Username",
                password = "Password",
                error = null
            ),
            onUiEvent = {}
        )
    }

}