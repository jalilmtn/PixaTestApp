package com.example.detail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.detail.DetailScreen

const val detailsRoute = "details_route"
const val imageIdArg = "imageId"

fun NavController.navigateToDetails(id: Long) {
    this.navigate("$detailsRoute?$imageIdArg=$id")
}


fun NavGraphBuilder.detailsScreen() {
    composable(
        route = "$detailsRoute?$imageIdArg={$imageIdArg}",
        arguments = listOf(navArgument(imageIdArg, builder = {
            type = NavType.LongType
        }))
    ) {
        DetailScreen(
            hiltViewModel()
        )
    }
}
