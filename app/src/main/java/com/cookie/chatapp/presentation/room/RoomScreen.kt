package com.cookie.chatapp.presentation.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cookie.chatapp.presentation.room.components.MessageInputField
import com.cookie.chatapp.presentation.room.model.UiEvent
import com.cookie.chatapp.presentation.room.model.UiState
import com.cookie.chatapp.presentation.theme.ChatAppTheme

@Composable
fun RoomScreen(
    viewModel: RoomVM,
    navigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    RoomScreen(
        uiState = uiState,
        uiEvent = {event->
            when(event){
                UiEvent.OnBackClicked -> {
                    navigateUp()
                }
                else -> {viewModel.onUiEvent(event)}
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoomScreen(
    uiState: UiState,
    uiEvent: (UiEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            uiState.roomCode,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                        Spacer(Modifier.height(1.dp))
                        Text(
                            "Last activity",
                            fontSize = 15.sp,
                            maxLines = 1,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Profile"
                        )
                    }
                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = { } //FIXME
                    ) {
                        DropdownMenuItem(
                            text = { Text("Leave Room") },
                            onClick = { }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete Room") },
                            onClick = { }
                        )
                        DropdownMenuItem(
                            text = { Text("Members") },
                            onClick = { }
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {uiEvent(UiEvent.OnBackClicked)}
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            MessageInputField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.message,
                onValueChange = {updatedValue->
                    uiEvent(UiEvent.OnMessageTyped(updatedValue))
                },
                onButtonClicked = {
                    uiEvent(UiEvent.OnMessageSendClicked)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(uiState.allMessages){message->
                    Message(
                        username = message.username,
                        msg = message.content,
                        isSentByUser = (uiState.userId == message.userId),
                    )
                    Spacer(Modifier.height(1.dp))
                    Text(
                        "Send by",
                        fontSize = 10.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }

        }
    }
}


@Composable
private fun Message(username: String, msg: String, isSentByUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isSentByUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.padding(4.dp),
            shape = RoundedCornerShape(4.dp),
        ) {
            Text(
                text = username,
                modifier = Modifier
                    .padding(start = 16.dp, top = 5.dp, end = 16.dp),
                textAlign = TextAlign.Left,
                fontSize = 15.sp
            )
            Text(
                text = msg,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 5.dp, end = 16.dp),
                textAlign = TextAlign.Left,
                fontSize = 14.sp
            )
        }
    }

}

//@Preview
//@Composable
//private fun AllRoomScreenPrev() {
//    ChatAppTheme {
//        RoomScreen(
//            Ui
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun AllRoomScreenPrev2() {
//    ChatAppTheme(darkTheme = true) {
//        RoomScreen()
//    }
//}