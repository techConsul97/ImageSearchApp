package com.sebqv97.imagesearchapp.firebase_auth.presentation

import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.core.util.ErrorTypes

data class AuthUserState(
    val user: AuthUser? = null,
    val encounteredError: ErrorTypes? = null,
    val isLoading: Boolean = false
)
