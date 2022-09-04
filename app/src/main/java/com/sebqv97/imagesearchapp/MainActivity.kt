package com.sebqv97.imagesearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sebqv97.imagesearchapp.firebase_auth.presentation.AuthScreen
import com.sebqv97.imagesearchapp.firebase_auth.presentation.LoginScreen
import com.sebqv97.imagesearchapp.firebase_auth.presentation.RegisterScreen
import com.sebqv97.imagesearchapp.ui.theme.ImageSearchAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // LoginScreen(Modifier)
                    RegisterScreen(modifier = Modifier)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageSearchAppTheme {
    }
}