package com.sebqv97.imagesearchapp.firebase_auth.presentation

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.common.api.ApiException
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sebqv97.imagesearchapp.R
import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.firebase_auth.domain.util.AuthResultContract
import com.sebqv97.imagesearchapp.firebase_auth.presentation.auth_activities.AuthActivity
import com.sebqv97.imagesearchapp.firebase_auth.presentation.component.ButtonInput
import com.sebqv97.imagesearchapp.firebase_auth.presentation.component.TextInput
import com.sebqv97.imagesearchapp.firebase_auth.presentation.destinations.HomeScreenDestination
import com.sebqv97.imagesearchapp.firebase_auth.presentation.destinations.RegisterScreenDestination
import com.sebqv97.imagesearchapp.firebase_auth.presentation.util.InputType
import com.sebqv97.imagesearchapp.core.ui.theme.Shapes
import kotlinx.coroutines.launch


@Destination(start = true)
@Composable
fun LoginScreen(

    //New way of Navigation
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: FirebaseAuthViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(viewModel) { viewModel.authState }
    val signInRequestCode = 1
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        viewModel.googleSignIn(
                            email = account.email!!,
                            displayName = account.displayName!!
                        )
                        navigator.navigate(HomeScreenDestination)
                    }
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
            }
        }

if(user.user == null){
    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo1),
            null,
            Modifier
                .size(80.dp)
                .background(Color.Transparent)
                .clip(Shapes.medium),
            contentScale = ContentScale.Crop,
            alpha = 0.5f

        ) //DONT FORGET


        TextInput(
            InputType.Name,
            getValue = { email.value = it },
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequester.requestFocus()
                })
        )

        TextInput(
            InputType.Password,
            getValue = { password.value = it },
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                viewModel.loginUser(AuthUser(email.value, password.value))
            }),
            focusRequester = passwordFocusRequester
        )


        Button(
            onClick = { viewModel.loginUser(AuthUser(email.value, password.value)) },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = "SIGN IN", modifier = modifier.padding(vertical = 8.dp))
        }

//        Button(
//            onClick = { //viewModel.loginUser(AuthUser(email.value, password.value))
//                 },
//            modifier = modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(painter = painterResource(id = R.drawable.github_icon,),
//                    contentDescription = "github_icon",
//                    modifier = modifier.size(40.dp).clip(RoundedCornerShape(50))
//                )
//                Text(text = "SIGN IN WITH GITHUB", modifier = modifier.padding(vertical = 8.dp), color = Color.Black)
//            }
//
//        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                ButtonInput(imageRes = R.drawable.github_icon,
                    callbackFunction = {context.startActivity(Intent(context, AuthActivity::class.java))}, modifier = modifier)//GITHUB
                ButtonInput(
                    imageRes = R.drawable.google_icon,
                    callbackFunction = {
                            authResultLauncher.launch(signInRequestCode)
                    },
                    modifier = modifier
                )//GOOGLE


            }


        }
        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = modifier.padding(top = 40.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?", color = Color.White)
            TextButton(onClick =
            {
                // Usage of the new Navigator
                navigator.navigate(RegisterScreenDestination)
            }
            ) {
                Text("SIGN UP")
            }
        }

    }
}else{
    navigator.navigate(HomeScreenDestination)
}

}

@Preview
@Composable
fun LoginPreview() {
    //LoginScreen(modifier = Modifier)

}