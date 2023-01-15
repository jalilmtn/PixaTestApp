package com.example.feed

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.common.coilImageRequest
import com.example.designsystem.component.ClearTextIcon
import com.example.designsystem.component.PixaTextInput
import com.example.designsystem.component.PlaceholderText
import com.example.designsystem.component.SearchLeadingIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedScreen(viewModel: FeedViewModel,) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error, block = {
        if (state.error.isNotEmpty())
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    })

    Column(modifier = Modifier.fillMaxSize()) {
        PixaTextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = viewModel.searchTxt.value,
            onValueChange = viewModel::setName,
            placeholder = {
                PlaceholderText(text = "fruits")
            },
            leadingIcon = {
                SearchLeadingIcon()
            },
            trailingIcon = {
                if (state.isLoading)
                    CircularProgressIndicator(modifier = Modifier.padding(4.dp))
                else
                    if (viewModel.searchTxt.value.isNotEmpty())
                        ClearTextIcon {
                            viewModel.setName("")
                        }

            },
        )

        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                state.imageList,
                key = {
                    it.id
                },
            ) {
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        model = coilImageRequest(
                            context = context,
                            data = it.previewURL
                        ),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                    )
                    Text(text = it.user, style = MaterialTheme.typography.labelMedium)
                    Text(text = it.tags, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}