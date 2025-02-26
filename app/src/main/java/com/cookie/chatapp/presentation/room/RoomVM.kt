package com.cookie.chatapp.presentation.room

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.models.MessageModel
import com.cookie.chatapp.domain.models.MessageTimestamp
import com.cookie.chatapp.domain.models.RoomEvent
import com.cookie.chatapp.domain.repository.RoomRepository
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.room.model.UiEvent
import com.cookie.chatapp.presentation.room.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
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
        userId = "",
    ))

    val uiState = _uiState.asStateFlow()

    private val roomCode = saveState.get<String>("code")

    init {
        viewModelScope.launch {
            roomRepository.connect()
            launch {
                listenToRoomEvents()
            }
            initUiState()
        }
    }

    private suspend fun initUiState(){
        val userId = userRepository.getLoggedInUserId()!!
        if (roomCode != null){
            _uiState.update {
                UiState(
                    roomCode = roomCode,
                    message = "",
                    allMessages = roomRepository.getAllMessages(roomCode),
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
                _uiState.update {
                    it.copy(
                        message = ""
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

    private suspend fun listenToRoomEvents(): Unit = withContext(Dispatchers.IO){
        roomRepository.getRoomEvents().collect({roomEvent->
            when(roomEvent){
                RoomEvent.Connected -> {Log.d("VM", "connected")}
                is RoomEvent.MessageReceived -> {
                    _uiState.update { it.copy(allMessages = it.allMessages + roomEvent.message)}
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
