package com.cookie.chatapp.presentation.user

import androidx.lifecycle.ViewModel
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.user.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UserVM @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(
        UiState(
            username = "username",
            firstName = "firstName",
            lastName = "lastName",
            email = "email",
            password = "password"
        )
    )
    val uiState = _uiState.asStateFlow()
}