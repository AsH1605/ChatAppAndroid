package com.cookie.chatapp.domain.models

sealed interface RoomEvent {
    data object Connected: RoomEvent
    data class MessageReceived(val message: MessageModel): RoomEvent
    data class UserJoined(val userModel: UserModel): RoomEvent
}