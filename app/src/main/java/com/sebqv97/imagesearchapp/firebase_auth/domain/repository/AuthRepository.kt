package com.sebqv97.imagesearchapp.firebase_auth.domain.repository

import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(auth: AuthUser): Flow<ResultState<String>>

    fun loginUser(auth: AuthUser):Flow<ResultState<String>>
}