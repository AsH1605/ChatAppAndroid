package com.cookie.chatapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.login.model.UiEvent
import com.cookie.chatapp.presentation.login.model.UiState
import com.cookie.chatapp.presentation.login.model.VmEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState("", "", null))
    val uiState = _uiState.asStateFlow()

    private val _vmEvent = MutableSharedFlow<VmEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            UiEvent.OnDismissError -> {
                _uiState.update { it.copy(error = null) }
            }
            UiEvent.OnLoginClicked -> {
                viewModelScope.launch{ login() }
            }
            is UiEvent.OnPasswordChange -> {
                _uiState.update { it.copy(password = uiEvent.updatedPassword) }
            }
            UiEvent.OnRegisterClicked -> {
                viewModelScope.launch {
                    _vmEvent.emit(VmEvent.NavigateToRegisterScreen)
                }
            }
            is UiEvent.OnUsernameChange -> {
                _uiState.update { it.copy(username = uiEvent.updatedUsername) }
            }
        }
    }

    private suspend fun login() {
        val username = uiState.value.username
        val password = uiState.value.password
        if(username == "" || password == ""){
            _uiState.update { it.copy(error = "All fields are required") }
            return
        }
        val loginSuccessful = userRepository.loginUser(username, password)
        if (!loginSuccessful){
            _vmEvent.emit(VmEvent.NavigateToRegisterScreen)
        }
        else{
            _vmEvent.emit(VmEvent.NavigateToRoomScreen)
        }
    }
}