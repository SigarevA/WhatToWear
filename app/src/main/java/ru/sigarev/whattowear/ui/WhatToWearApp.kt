package ru.sigarev.whattowear.ui

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import ru.sigarev.whattowear.ui.home.NavGraphs

@Composable
fun WhatToWearApp() {
    val engine = rememberNavHostEngine()
    val navController = engine.rememberNavController()

    val startRoute = NavGraphs.root.startRoute

    DestinationsNavHost(
        engine = engine,
        navController = navController,
        navGraph = NavGraphs.root,
        startRoute = startRoute
    )
}