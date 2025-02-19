package com.cookie.chatapp.presentation.register

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookie.chatapp.R
import com.cookie.chatapp.presentation.theme.ChatAppTheme
import com.cookie.chatapp.presentation.register.model.UiEvent
import com.cookie.chatapp.presentation.register.model.UiState

@Composable
fun UserRegistrationScreen(
    viewModel: UserVM
) {
    val uiState by viewModel.uiState.collectAsState()
    UserRegisterScreen(
        uiState = uiState,
        onUiEvent = {event->
            viewModel.onUiEvent(event)
        }
    )
}

@Composable
private fun UserRegisterScreen(
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
            value = uiState.firstName,
            onValueChange = {updatedFirstName->
                onUiEvent(UiEvent.OnFirstNameChange(updatedFirstName))
            },
            placeholder = {
                Text("FirstName")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.lastName,
            onValueChange = {updatedLastName->
                onUiEvent(UiEvent.OnLastNameChange(updatedLastName))
            },
            placeholder = {
                Text("LastName")
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.email,
            onValueChange = {updatedEmail->
                onUiEvent(UiEvent.OnEmailChange(updatedEmail))
            },
            placeholder = {
                Text("Email")
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
                onUiEvent(UiEvent.OnRegisterClicked)
            }
        ) {
            Text(
                "Register!",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun PreviewUserRegistrationScreen() {
    ChatAppTheme {
        UserRegisterScreen(
            uiState = UiState(
                username = "Username",
                firstName = "FirstName",
                lastName = "LastName",
                email = "Email",
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
        UserRegisterScreen(
            uiState = UiState(
                username = "Username",
                firstName = "FirstName",
                lastName = "LastName",
                email = "Email",
                password = "Password",
                error = null
            ),
            onUiEvent = {}
        )
    }

}