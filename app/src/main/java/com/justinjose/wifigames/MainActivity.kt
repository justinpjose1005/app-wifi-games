package com.justinjose.wifigames

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.justinjose.wifigames.navigation.SetupNavController
import com.justinjose.wifigames.ui.theme.AppWifiGamesTheme
import com.justinjose.wifigames.ui.viewmodels.GamesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetTheme(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            when (resultCode) {
                RESULT_OK -> {
                }
                RESULT_CANCELED -> {
                    FirebaseEvent.onAppUpdateCancelled()
                    Toast.makeText(this, "App update was declined.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else -> {
                }
            }
        }
    }

    @Composable
    fun SetTheme(activity: Activity, gamesViewModel: GamesViewModel = viewModel()) {
        val isDarkTheme = gamesViewModel.isDarkTheme.collectAsState().value
        AppWifiGamesTheme(darkTheme = isDarkTheme) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                SetupNavController(activity, rememberNavController())
            }
        }
    }
}