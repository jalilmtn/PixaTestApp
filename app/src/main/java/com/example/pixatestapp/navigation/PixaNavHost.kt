package com.example.pixatestapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.feed.navigation.feedRoute
import com.example.feed.navigation.feedScreen

@Composable
fun PixaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = feedRoute,
        modifier = modifier,
        builder = {
            feedScreen()
        }
    )
}