package com.example.feed

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun FeedScreenRoute(
    viewModel: FeedViewModel,
    onImageClick: (Long) -> Unit,
) {
    val state = viewModel.viewState.value
    val imageListState = viewModel.viewState.value.imageList.collectAsState(initial = emptyList())
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error, block = {
        if (state.error.isNotEmpty())
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    })

    FeedScreen(
        state = state,
        imageListState = imageListState,
        onImageClick = onImageClick,
        setName = viewModel::setName,
        searchTxt = viewModel.searchTxt
    )


}