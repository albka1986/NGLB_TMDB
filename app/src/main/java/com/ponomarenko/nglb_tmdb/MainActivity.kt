package com.ponomarenko.nglb_tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ponomarenko.nglb_tmdb.ui.screen.MainScreen
import com.ponomarenko.nglb_tmdb.ui.theme.NGLB_TMDBTheme
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NGLB_TMDBTheme {
                MainScreen()
            }
        }
    }
}
