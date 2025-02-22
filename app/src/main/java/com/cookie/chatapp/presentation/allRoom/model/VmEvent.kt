package com.cookie.chatapp.presentation.allRoom.model

sealed interface VmEvent {
    data object NavigateToLoginScreen: VmEvent
}