package com.cookie.chatapp.presentation.room

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.models.MessageModel
import com.cookie.chatapp.domain.models.RoomEvent
import com.cookie.chatapp.domain.repository.RoomRepository
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.room.model.UiEvent
import com.cookie.chatapp.presentation.room.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomVM @Inject constructor(
    private val roomRepository: RoomRepository,
    private val saveState: SavedStateHandle,
    private val userRepository: UserRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(UiState(
        roomCode = "",
        message = "",
        allMessages = listOf<MessageModel>(),
        userId = ""
    ))

    private var allMessageList = mutableListOf<MessageModel>()

    val uiState = _uiState.asStateFlow()

    private val roomCode = saveState.get<String>("code")

    init {
        viewModelScope.launch {
            roomRepository.connect()
            launch {
                listenToRoomEvents()
            }
            initUiState()
            roomRepository.sendMessage("RAXAWA","hi", "BDBDG")
        }
    }

    private suspend fun initUiState(){
        val userId = userRepository.getLoggedInUserId()!!
        if (roomCode != null){
            _uiState.update {
                UiState(
                    roomCode = roomCode,
                    message = "",
                    allMessages = allMessageList,
                    userId = userId
                )
            }
        }
    }

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            UiEvent.OnBackClicked -> {}
            UiEvent.OnMessageSendClicked -> viewModelScope.launch{
                val message = uiState.value.message
                val username = userRepository.getUsername()
                if (roomCode != null) {
                    roomRepository.sendMessage(
                        roomCode = roomCode,
                        username = username,
                        message = message,
                    )
                }
            }
            is UiEvent.OnMessageTyped -> {
                _uiState.update { it.copy(
                    message = uiEvent.message
                )}
            }
        }
    }

    private suspend fun listenToRoomEvents(){
        roomRepository.getRoomEvents().collect({roomEvent->
            when(roomEvent){
                RoomEvent.Connected -> {Log.d("VM", "connected")}
                is RoomEvent.MessageReceived -> {
                    allMessageList.add(roomEvent.message)
                }
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
