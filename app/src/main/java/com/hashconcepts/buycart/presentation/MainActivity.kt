package com.hashconcepts.buycart.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hashconcepts.buycart.presentation.navigation.SetupRootNavigation
import com.hashconcepts.buycart.ui.theme.BuyCartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyCartTheme {
                SetupRootNavigation()
            }
        }
    }
}
