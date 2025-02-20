package com.cookie.chatapp.presentation.login.model

sealed interface VmEvent {
    data object NavigateToRegisterScreen: VmEvent
    data object NavigateToRoomScreen: VmEvent
}