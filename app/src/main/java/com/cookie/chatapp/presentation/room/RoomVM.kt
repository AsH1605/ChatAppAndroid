package com.cookie.chatapp.presentation.room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.models.RoomEvent
import com.cookie.chatapp.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomVM @Inject constructor(
    private val roomRepository: RoomRepository
): ViewModel(){

    init {
        viewModelScope.launch {
            roomRepository.connect()
            launch {
                listenToRoomEvents()
            }
            roomRepository.sendMessage("RAXAWA","hi", "BDBDG")
        }
    }

    private suspend fun listenToRoomEvents(){
        roomRepository.getRoomEvents().collect({roomEvent->
            when(roomEvent){
                RoomEvent.Connected -> {Log.d("VM", "connected")}
                is RoomEvent.MessageReceived -> {Log.d("VM", "message agya ${roomEvent.message.content}")}
                is RoomEvent.UserJoined -> {Log.d("VM", "joined room ${roomEvent.userModel.username}")}
            }
        })
    }

    override fun onCleared() {
        viewModelScope.launch {
            roomRepository.disconnect()
        }
        super.onCleared()
    }
}
