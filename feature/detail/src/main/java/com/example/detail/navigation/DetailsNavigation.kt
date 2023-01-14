package com.example.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.detail.DetailScreen

const val detailsRoute = "details_route"


fun NavController.navigateToDetails() {
    this.navigate(detailsRoute)
}


fun NavGraphBuilder.detailsScreen() {
    composable(detailsRoute) {
        DetailScreen()
    }
}