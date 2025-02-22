package com.cookie.chatapp.presentation.allRoom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.repository.AllRoomRepository
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.allRoom.model.UiEvent
import com.cookie.chatapp.presentation.allRoom.model.UiState
import com.cookie.chatapp.presentation.allRoom.model.VmEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllRoomVM @Inject constructor(
    private val allRoomRepository: AllRoomRepository,
    private val userRepository: UserRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(UiState("", listOf(), false))
    val uiState = _uiState.asStateFlow()

    private val _vmEvent = MutableSharedFlow<VmEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            UiEvent.OnAddClicked -> {
                allRoomRepository
            }
            UiEvent.OnProfileClicked -> {
                _uiState.update {
                    it.copy(
                        isContextMenuVisible = !it.isContextMenuVisible
                    )
                }
            }
            is UiEvent.OnRoomClicked -> TODO()
            UiEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    userRepository.logoutUser()
                    _vmEvent.emit(VmEvent.NavigateToLoginScreen)
                }
            }
        }
    }

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
                room = room,
                isContextMenuVisible = false,
            )
        }
    }
}