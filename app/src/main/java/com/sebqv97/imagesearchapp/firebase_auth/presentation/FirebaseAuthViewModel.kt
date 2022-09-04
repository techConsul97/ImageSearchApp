package com.sebqv97.imagesearchapp.firebase_auth.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.firebase_auth.domain.use_case.AuthorizeUserUseCase
import com.sebqv97.imagesearchapp.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    private val authorizeUserUseCase: AuthorizeUserUseCase
) : ViewModel() {

    private val _authState: MutableState<AuthUserState> = mutableStateOf(value = AuthUserState())
    val authState get() = _authState

    fun createUserWithCredentials(authUser: AuthUser) {
        viewModelScope.launch {
            authorizeUserUseCase.createUser(authUser).collect {
                when (it) {
                    is ResultState.Success -> {
                        loginUser(authUser)
                    }
                    is ResultState.Error -> {
                        _authState.value = AuthUserState(encounteredError = it.errorType)
                    }
                    is ResultState.Loading -> {
                        _authState.value = AuthUserState(isLoading = true)
                    }
                }
            }
        }
    }

    fun loginUser(authUser: AuthUser) {
        viewModelScope.launch {
            authorizeUserUseCase.loginUser(authUser).collect {
                when (it) {
                    is ResultState.Success -> {
                        _authState.value = AuthUserState(isSuccessful = true)
                    }
                    is ResultState.Error -> {
                        _authState.value = AuthUserState(encounteredError = it.errorType)
                    }
                    is ResultState.Loading -> {
                        _authState.value = AuthUserState(isLoading = true)
                    }
                }
            }
        }
    }
}

