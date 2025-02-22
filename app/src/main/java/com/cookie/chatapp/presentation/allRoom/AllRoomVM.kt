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
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(
        "",
        listOf(),
        false,
        false,
        false,
        "",
        "",
        false
    ))
    val uiState = _uiState.asStateFlow()

    private val _vmEvent = MutableSharedFlow<VmEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    fun onUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            UiEvent.OnFabClicked -> {
                _uiState.update {
                    it.copy(
                        isExpanded = !it.isExpanded
                    )
                }
            }

            UiEvent.OnProfileClicked -> {
                _uiState.update {
                    it.copy(
                        isContextMenuVisible = !it.isContextMenuVisible
                    )
                }
            }

            is UiEvent.OnRoomClicked -> {}
            UiEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    userRepository.logoutUser()
                    _vmEvent.emit(VmEvent.NavigateToLoginScreen)
                }
            }

            is UiEvent.OnJoinRoomClicked ->
                viewModelScope.launch {
                    if (uiState.value.roomCode != ""){
                        allRoomRepository.joinRoom(uiState.value.roomCode)
                        loadRooms()
                    }
                    _uiState.update {
                        it.copy(
                            isJoinRoomDialogVisible = !it.isJoinRoomDialogVisible
                        )
                    }
                }
            is UiEvent.OnCreateRoomClicked ->
                viewModelScope.launch {
                    if (uiState.value.description != ""){
                        allRoomRepository.createRoom(uiState.value.description)
                        loadRooms()
                    }

                    _uiState.update {
                        it.copy(
                            isCreateRoomDialogVisible = !it.isCreateRoomDialogVisible
                        )
                }
            }
            UiEvent.OnCreateDialogButtonClicked -> {
                _uiState.update {
                    it.copy(
                        isCreateRoomDialogVisible = !it.isCreateRoomDialogVisible
                    )
                }
            }
            UiEvent.OnJoinDialogButtonClicked -> {
                _uiState.update {
                    it.copy(
                        isJoinRoomDialogVisible = !it.isJoinRoomDialogVisible
                    )
                }
            }

            is UiEvent.OnDescriptionUpdate -> {
                _uiState.update {
                    it.copy(
                        description = uiEvent.description
                    )
                }
            }
            is UiEvent.OnRoomCodeUpdate -> {
                _uiState.update {
                    it.copy(
                        roomCode = uiEvent.roomCode
                    )
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            initUiState()
        }
    }

    private suspend fun loadRooms(){
        val rooms = allRoomRepository.getAllRooms()
        _uiState.update {
            it.copy(
                room = rooms
            )
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
                isJoinRoomDialogVisible = false,
                isCreateRoomDialogVisible = false,
                roomCode = "",
                description = "",
                isExpanded = false,
            )
        }
    }
}