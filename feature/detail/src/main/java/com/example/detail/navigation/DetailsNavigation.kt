package com.example.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.detail.DetailScreen
import com.example.domain.model.Image

const val detailsRoute = "details_route"
const val imageArg = "image"

fun NavController.navigateToDetails(image: Image) {
    this.currentBackStackEntry?.savedStateHandle?.set(
        imageArg,
        image
    )
    this.navigate(detailsRoute)
}


fun NavGraphBuilder.detailsScreen(navController: NavController) {
    composable(
        route = detailsRoute,
    ) {
        DetailScreen(
            image = navController.previousBackStackEntry?.savedStateHandle?.get<Image>(
                imageArg
            ),
        )
    }
}
