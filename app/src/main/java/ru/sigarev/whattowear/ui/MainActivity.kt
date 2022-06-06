package ru.sigarev.whattowear.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint
import ru.sigarev.whattowear.ui.theme.WhatToWearTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("a7a7e2dd-988c-4303-a175-30499d1a2143")
        MapKitFactory.initialize(this)

        setContent {
            WhatToWearTheme {
                Surface(color = MaterialTheme.colors.background) {
                    WhatToWearApp()
                }
            }
        }
    }
}