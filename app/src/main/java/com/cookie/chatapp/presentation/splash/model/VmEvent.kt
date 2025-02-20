package com.cookie.chatapp.presentation.splash.model

sealed interface VmEvent {
    data object NavigateToLoginScreen: VmEvent
    data object NavigateToChatScreen: VmEvent
}