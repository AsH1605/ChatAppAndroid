package com.cookie.chatapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.models.UserModel
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.register.model.UiEvent
import com.cookie.chatapp.presentation.register.model.UiState
import com.cookie.chatapp.presentation.register.model.VmEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(
        UiState(
            username = "",
            firstName = "",
            lastName = "",
            email = "",
            password = "",
            error = null
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _vmEvent = MutableSharedFlow<VmEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            is UiEvent.OnEmailChange -> {
                _uiState.update { it.copy(email = uiEvent.updatedEmail) }
            }
            is UiEvent.OnFirstNameChange -> {
                _uiState.update { it.copy(firstName = uiEvent.updatedFirstName) }
            }
            is UiEvent.OnLastNameChange -> {
                _uiState.update { it.copy(lastName = uiEvent.updatedLastName) }
            }
            is UiEvent.OnPasswordChange -> {
                _uiState.update { it.copy(password = uiEvent.updatedPassword) }
            }
            UiEvent.OnRegisterClicked -> {
                viewModelScope.launch { register() }
            }
            is UiEvent.OnUsernameChange -> {
                _uiState.update { it.copy(username = uiEvent.updatedUsername) }
            }
            UiEvent.OnDismissError -> {
                _uiState.update { it.copy(error = null) }
            }
        }
    }

    private suspend fun register() {
        val username = uiState.value.username
        val email = uiState.value.email
        val password = uiState.value.password
        val firstName = uiState.value.firstName
        val lastName = uiState.value.lastName
        if(username == "" || email == ""||
            password == "" || firstName == "" ||
            lastName == ""
            ){
            _uiState.update { it.copy(error = "All fields are required") }
            return
        }
        val response = userRepository.registerUser(UserModel(
            username = username,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password
        ))
        if(response){
            _vmEvent.emit(VmEvent.NavigateToLoginScreen)
        }
    }
}