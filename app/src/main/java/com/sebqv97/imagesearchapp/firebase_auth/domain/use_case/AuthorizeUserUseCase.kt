package com.sebqv97.imagesearchapp.firebase_auth.domain.use_case

import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.firebase_auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthorizeUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    fun createUser(authUser: AuthUser) = authRepository.createUser(authUser)

    fun loginUser(authUser: AuthUser) = authRepository.loginUser(authUser)
}