package com.example.feed.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feed.FeedScreen


const val feedRoute = "feed_route"

fun NavGraphBuilder.feedScreen() {
    composable(feedRoute) {
        FeedScreen(hiltViewModel())
    }
}