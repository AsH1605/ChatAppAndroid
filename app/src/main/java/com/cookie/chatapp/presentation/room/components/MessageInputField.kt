package com.cookie.chatapp.presentation.room.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            onValueChange = {updatedValue->
                onValueChange(updatedValue)
            },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .border(2.dp, color = Color.DarkGray, RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {innerTextField() }
            },
            modifier = Modifier.weight(1f)
        )
        IconButton (
            onClick = {onButtonClicked()}
        ){
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
        }
    }
}
