package com.sebqv97.imagesearchapp.firebase_auth.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.firebase_auth.domain.repository.AuthRepository
import com.sebqv97.imagesearchapp.util.Constants
import com.sebqv97.imagesearchapp.util.ErrorTypes
import com.sebqv97.imagesearchapp.util.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth:FirebaseAuth
): AuthRepository {
    override fun createUser(auth: AuthUser): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading<String>())

        firebaseAuth.createUserWithEmailAndPassword(auth.email!!,auth.password!!).addOnCompleteListener{
            if(it.isSuccessful) {
                trySend(ResultState.Success("User Created Successfully"))
                Log.d(Constants.CREATE_USER_TAG, "Current UserID: ${firebaseAuth.currentUser?.uid}")
            }
        }.addOnFailureListener {
            trySend(ResultState.Error<String>(ErrorTypes.UserRegistrationFailed()))
        }
        awaitClose{ close() }
    }


    override fun loginUser(auth: AuthUser): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading<String>())
         firebaseAuth.signInWithEmailAndPassword(auth.email!!,auth.password!!)
             .addOnCompleteListener {
                 trySend(ResultState.Success<String>("Login successful"))
             }.addOnFailureListener {
                 trySend(ResultState.Error<String>(ErrorTypes.UserLoginFailed()))
             }
        awaitClose{
            close()
        }

    }
}