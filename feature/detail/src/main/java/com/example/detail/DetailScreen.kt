package com.example.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.common.coilImageRequest
import com.example.designsystem.component.DialogBoxLoading


@Composable
fun DetailScreen(
    viewModel: DetailsViewModel
) {
    val context = LocalContext.current
    var isMediaLoaded by remember { mutableStateOf(false) }
    val state = viewModel.viewState

    state.value?.let { image ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = coilImageRequest(
                    context = context,
                    data = image.largeImageURL,
                    onError = { _, _ ->
                        Toast.makeText(
                            context,
                            context.getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onSuccess = { _, _ ->
                        isMediaLoaded = true
                    }
                ),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            if (isMediaLoaded) {
                Row(
                    Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite, contentDescription = null,
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = image.likes.toString(), style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = Modifier.size(4.dp))
                    Icon(
                        painterResource(id = R.drawable.ic_download),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = image.downloads.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Icon(
                        painterResource(id = R.drawable.ic_chat),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = image.comments.toString(),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(1.dp)
                        .background(color = MaterialTheme.colorScheme.onBackground)
                )
                Column(Modifier.padding(8.dp)) {
                    Text(
                        text = image.user,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = image.tags,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

            } else {
                DialogBoxLoading()
            }
        }
    }

}