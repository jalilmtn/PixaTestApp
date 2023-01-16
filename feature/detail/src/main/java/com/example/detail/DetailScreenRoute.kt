package com.example.detail

import androidx.compose.runtime.Composable


@Composable
internal fun DetailScreenRoute(
    viewModel: DetailsViewModel
) {
   val state = viewModel.viewState
    state.value?.let { image ->
        DetailScreen(image)
    }

}