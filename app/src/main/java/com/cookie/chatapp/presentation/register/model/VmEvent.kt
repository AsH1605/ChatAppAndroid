package com.cookie.chatapp.presentation.register.model

sealed interface VmEvent {
    data object NavigateToLoginScreen: VmEvent
}