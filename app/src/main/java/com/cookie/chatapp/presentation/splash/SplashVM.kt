package com.cookie.chatapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.chatapp.domain.repository.UserRepository
import com.cookie.chatapp.presentation.splash.model.VmEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _vmEvent = MutableStateFlow<VmEvent?>(null)
    val vmEvent = _vmEvent.asStateFlow()

    init {
        viewModelScope.launch{ checkLoginStatus() }
    }

    private suspend fun checkLoginStatus() {
        val userId = userRepository.getLoggedInUserId()

        if(userId == null){
            _vmEvent.emit(VmEvent.NavigateToLoginScreen)
        }
        else{
            _vmEvent.emit(VmEvent.NavigateToChatScreen)
        }
    }
}