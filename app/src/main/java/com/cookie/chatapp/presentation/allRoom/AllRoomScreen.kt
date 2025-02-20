package com.cookie.chatapp.presentation.allRoom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cookie.chatapp.presentation.login.model.UiEvent
import com.cookie.chatapp.presentation.theme.ChatAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllRoomScreen(
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Welcome, User",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }
                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = {  } //FIXME
                    ) {
//                        DropdownMenuItem(
//                            text = { Text("Logout" ) },
//                            onClick = { onUiEvent(UiEvent.OnLogoutClicked) }
//                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note",
                    modifier = Modifier.size(23.dp)
                )
            }
        }
    ){padding->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Room()
        }
    }
}

@Composable
private fun Room() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    ) {
        Text(
            text = "Room Name",
            modifier = Modifier
                .padding(start = 16.dp, top = 5.dp),
            textAlign = TextAlign.Left,
            fontSize = 15.sp
        )
        Text(
            text = "No of messages = ",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 5.dp),
            textAlign = TextAlign.Left,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
private fun AllRoomScreenPrev() {
    ChatAppTheme {
        AllRoomScreen()
    }
}

@Preview
@Composable
private fun AllRoomScreenPrev2() {
    ChatAppTheme(darkTheme = true) {
        AllRoomScreen()
    }
}