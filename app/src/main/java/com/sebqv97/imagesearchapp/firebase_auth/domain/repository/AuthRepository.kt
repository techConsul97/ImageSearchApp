package com.sebqv97.imagesearchapp.firebase_auth.domain.repository

import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun createUser(auth: AuthUser): Flow<ResultState<String>>

    suspend fun loginUser(auth: AuthUser): Flow<ResultState<String>>


}