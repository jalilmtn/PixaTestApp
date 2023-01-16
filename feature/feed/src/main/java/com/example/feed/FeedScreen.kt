package com.example.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.common.Constants.TOPIC_LOADING
import com.example.common.coilImageRequest
import com.example.designsystem.component.ClearTextIcon
import com.example.designsystem.component.PixaAlertDialog
import com.example.designsystem.component.PixaTextInput
import com.example.designsystem.component.PlaceholderText
import com.example.designsystem.component.SearchLeadingIcon
import imagedb.ImageEntitiy

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun FeedScreen(
    state: ViewState,
    imageListState: State<List<ImageEntitiy>>,
    searchTxt: State<String>,
    onImageClick: (Long) -> Unit,
    setName: (String) -> Unit
) {
    val context = LocalContext.current
    var showGoToDetailsDialog by remember { mutableStateOf(DetailsDialogState(false)) }

    if (showGoToDetailsDialog.showDialog) {
        PixaAlertDialog(
            onNegativeClick = {
                showGoToDetailsDialog = DetailsDialogState(false)
            },
            onPositiveClick = {
                showGoToDetailsDialog.imageId?.let(onImageClick)
                showGoToDetailsDialog = DetailsDialogState(false)
            },
            dialogTitle = stringResource(R.string.show_details_dialog_title)
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {
        PixaTextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = searchTxt.value,
            onValueChange = setName,
            placeholder = {
                PlaceholderText(text = stringResource(R.string.search_image_placeholder_txt))
            },
            leadingIcon = {
                SearchLeadingIcon()
            },
            trailingIcon = {
                if (state.isLoading)
                    CircularProgressIndicator(modifier = Modifier
                        .semantics {
                            contentDescription = TOPIC_LOADING
                        }
                        .size(24.dp)
                    )
                else
                    if (searchTxt.value.isNotEmpty())
                        ClearTextIcon {
                            setName("")
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
                imageListState.value,
                key = {
                    it.id
                },
            ) {
                Column {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(it.previewWidth.toFloat() / it.previewHeight)
                            .clickable {
                                showGoToDetailsDialog = DetailsDialogState(true, it.id)
                            },
                        model = coilImageRequest(
                            context = context,
                            data = it.previewURL,
                        ),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                    )
                    Text(
                        text = it.user,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = it.tags, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

private data class DetailsDialogState(
    val showDialog: Boolean = false,
    val imageId: Long? = null
)