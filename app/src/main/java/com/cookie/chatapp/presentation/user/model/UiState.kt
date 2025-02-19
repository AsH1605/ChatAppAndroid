package com.cookie.chatapp.presentation.user.model

data class UiState(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val error: String?
)
