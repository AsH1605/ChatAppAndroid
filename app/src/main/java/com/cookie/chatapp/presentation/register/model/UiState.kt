package com.cookie.chatapp.presentation.register.model

data class UiState(
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val error: String?
)
