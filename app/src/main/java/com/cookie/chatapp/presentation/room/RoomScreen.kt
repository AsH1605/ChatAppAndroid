package com.cookie.chatapp.presentation.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cookie.chatapp.presentation.room.components.MessageInputField
import com.cookie.chatapp.presentation.theme.ChatAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "Room Name",
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
                        onClick = {}
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
                value = "",
                onValueChange = {}
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Message()
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


@Composable
private fun Message() {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = "Sender",
            modifier = Modifier
                .padding(start = 16.dp, top = 5.dp, end = 16.dp),
            textAlign = TextAlign.Left,
            fontSize = 15.sp
        )
        Text(
            text = "Message",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 5.dp, end = 16.dp),
            textAlign = TextAlign.Left,
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
private fun AllRoomScreenPrev() {
    ChatAppTheme {
        RoomScreen()
    }
}

@Preview
@Composable
private fun AllRoomScreenPrev2() {
    ChatAppTheme(darkTheme = true) {
        RoomScreen()
    }
}