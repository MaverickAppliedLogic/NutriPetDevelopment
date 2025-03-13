package com.example.feedm.petsFeature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.feedm.core.navigation.NavigationWrapper
import com.example.feedm.core.ui.theme.TailyCareTheme

class MainPetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TailyCareTheme {
                NavigationWrapper()
            }
        }
    }
}


