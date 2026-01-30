package com.patatus.patatuswallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.patatus.patatuswallet.core.ui.theme.PatatusWalletTheme
import android.app.Application
import com.patatus.patatuswallet.core.di.AppContainer
import com.patatus.patatuswallet.core.di.DefaultAppContainer
class MainActivity : ComponentActivity() {
    lateinit var container: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        container = DefaultAppContainer(this)
        enableEdgeToEdge()
        setContent {
            PatatusWalletTheme {

            }
        }
    }
}

