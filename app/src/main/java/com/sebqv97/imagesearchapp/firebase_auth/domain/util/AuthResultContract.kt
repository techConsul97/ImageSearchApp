package com.sebqv97.imagesearchapp.firebase_auth.domain.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class AuthResultContract
: ActivityResultContract<Int, Task<GoogleSignInAccount>?>() {
    @Inject lateinit var googleSignInClient:GoogleSignInClient

    override fun createIntent(context: Context, input: Int): Intent =
        getGoogleSignInClient(context).signInIntent

    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        return when (resultCode){
           0-> GoogleSignIn.getSignedInAccountFromIntent(intent)
            else -> null
        }

    }

}
