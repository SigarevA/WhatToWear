package ru.sigarev.whattowear.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import ru.sigarev.whattowear.R
import ru.sigarev.whattowear.ui.theme.WhatToWearTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatToWearTheme {
                Surface(color = MaterialTheme.colors.background) {
                    WhatToWearApp()
                }
            }
        }
    }
}