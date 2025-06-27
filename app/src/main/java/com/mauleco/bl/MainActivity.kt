package com.mauleco.bl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mauleco.bl.ui.theme.BlTheme
import com.mauleco.bl.ui.theme.components.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlTheme {
                DashboardScreen()
            }
        }
    }
}