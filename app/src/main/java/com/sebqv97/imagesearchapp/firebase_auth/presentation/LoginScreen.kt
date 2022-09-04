package com.sebqv97.imagesearchapp.firebase_auth.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser
import com.sebqv97.imagesearchapp.firebase_auth.presentation.component.TextInput
import com.sebqv97.imagesearchapp.firebase_auth.presentation.util.InputType

@Composable
fun LoginScreen(modifier: Modifier, viewModel: FirebaseAuthViewModel = hiltViewModel()) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon(painter = painterResource(id = ), contentDescription = ) //DONT FORGET
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
        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = modifier.padding(top = 40.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?", color = Color.White)
            TextButton(onClick = {}) {
                Text("SIGN UP")
            }
        }

    }

}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(modifier = Modifier)

}