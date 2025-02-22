package com.cookie.chatapp.presentation.allRoom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cookie.chatapp.presentation.allRoom.model.OptionModel

@Composable
fun CustomizedFabButton(
    isExpanded: Boolean,
    onAddClicked: () -> Unit,
    options: List<OptionModel>,
    onItemClicked: (index: Int) -> Unit
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.End
    ){
        if (isExpanded){
            options.forEachIndexed { index, optionModel ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        optionModel.name,
                        modifier = Modifier.background(Color.DarkGray),
                        color = Color.White
                    )
                    Spacer(Modifier.width(5.dp))
                    FloatingActionButton(
                        onClick = {onItemClicked(index)},
                        modifier = Modifier,
                        shape = RoundedCornerShape(4.dp)
                    ){
                        Icon(
                            imageVector = optionModel.imageVector,
                            contentDescription = null
                        )
                    }
                }

            }

        }

        ExtendedFloatingActionButton(
            onClick = {
                onAddClicked()
            }
        ) {
            Text(
                if (isExpanded) {
                    "x"
                }
                else {
                    "+"
                }
            )
        }
    }

}