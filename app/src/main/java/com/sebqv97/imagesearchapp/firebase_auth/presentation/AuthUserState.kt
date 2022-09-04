package com.sebqv97.imagesearchapp.firebase_auth.presentation

import com.sebqv97.imagesearchapp.util.ErrorTypes

data class AuthUserState(
    val isSuccessful: Boolean = false,
    val encounteredError: ErrorTypes? = null,
    val isLoading: Boolean = false
)
