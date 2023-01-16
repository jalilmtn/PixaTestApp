package com.example.feed.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feed.FeedScreenRoute


const val feedRoute = "feed_route"

fun NavGraphBuilder.feedScreen(onImageClick: (Long) -> Unit) {
    composable(feedRoute) {
        FeedScreenRoute(
            hiltViewModel(),
            onImageClick = onImageClick,
        )
    }
}