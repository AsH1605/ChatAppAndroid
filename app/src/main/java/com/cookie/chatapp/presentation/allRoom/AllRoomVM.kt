package com.cookie.chatapp.presentation.allRoom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.repository.AllRoomRepository
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.allRoom.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRoomVM @Inject constructor(
    private val allRoomRepository: AllRoomRepository,
    private val userRepository: UserRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(UiState("", listOf()))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            initUiState()
        }
    }

    private suspend fun initUiState() {
        val username = userRepository.getUsername()
        val room = allRoomRepository.getAllRooms()
        _uiState.update {
            UiState(
                username = username,
                room = room
            )
        }
    }
}