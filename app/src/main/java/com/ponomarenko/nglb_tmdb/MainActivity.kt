package com.ponomarenko.nglb_tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ponomarenko.nglb_tmdb.ui.screen.MainScreen
import com.ponomarenko.nglb_tmdb.ui.theme.NGLB_TMDBTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NGLB_TMDBTheme {
                MainScreen()
            }
        }
    }
}
