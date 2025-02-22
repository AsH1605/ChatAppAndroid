package com.cookie.chatapp.presentation.allRoom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cookie.chatapp.domain.models.RoomModel
import com.cookie.chatapp.presentation.allRoom.model.UiEvent
import com.cookie.chatapp.presentation.allRoom.model.UiState
import com.cookie.chatapp.presentation.theme.ChatAppTheme

@Composable
fun AllRoomScreen(viewmodel: AllRoomVM) {
    val uiState by viewmodel.uiState.collectAsState()
    AllRoomScreen(
        uiState = uiState,
        onUiEvent = {event->
            when(event){
                UiEvent.OnAddClicked -> TODO()
                UiEvent.OnProfileClicked -> TODO()
                is UiEvent.OnRoomClicked -> TODO()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AllRoomScreen(
    uiState: UiState,
    onUiEvent: (UiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Welcome, " + uiState.username,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {
                        onUiEvent(UiEvent.OnProfileClicked)
                    }) {
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
                onClick = {
                    onUiEvent(UiEvent.OnAddClicked)
                },
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
            LazyColumn {
                items(uiState.room){room->
                    Room(
                        room.code, room.totalMessages,
                        onClick = { onUiEvent(UiEvent.OnRoomClicked(room.code)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun Room(roomName: String, totalMessages: Int, onClick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        onClick = onClick
    ) {
        Text(
            text = roomName,
            modifier = Modifier
                .padding(start = 16.dp, top = 5.dp),
            textAlign = TextAlign.Left,
            fontSize = 15.sp
        )
        Text(
            text = "No of messages = $totalMessages",
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
        AllRoomScreen(
            uiState = UiState(
                username = "Ash",
                room = listOf(
                    RoomModel(
                        code = "zs242",
                        totalMessages = 5
                    ),
                    RoomModel(
                        code = "eg425",
                        totalMessages = 4
                    )
                )
            ),
            onUiEvent = {}
        )
    }
}

@Preview
@Composable
private fun AllRoomScreenPrev2() {
    ChatAppTheme(darkTheme = true) {
        AllRoomScreen(
            uiState = UiState(
                username = "Ash",
                room = listOf(
                    RoomModel(
                        code = "zs242",
                        totalMessages = 5
                    ),
                    RoomModel(
                        code = "eg425",
                        totalMessages = 4
                    )
                )
            ),
            onUiEvent = {}
        )
    }
}