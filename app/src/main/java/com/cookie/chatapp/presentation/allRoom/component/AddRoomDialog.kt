package com.cookie.chatapp.presentation.allRoom.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddRoomDialog(
    onDismissRequest: () -> Unit,
    content: String,
    action: String,
    onValueUpdate: (String) -> Unit,
    onClickEvent: () -> Unit,
    value: String
) {
    Dialog(
        onDismissRequest = { onDismissRequest()}
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp),
                value = value,
                onValueChange = {updatedValue->
                    onValueUpdate(updatedValue)
                },
                placeholder = { Text("Enter $content") }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp, top = 4.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = { onClickEvent() }
            ) {
                Text(action)
            }
        }
    }
}
