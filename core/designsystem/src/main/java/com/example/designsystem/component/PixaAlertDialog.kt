package com.example.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.designsystem.R

@Composable
fun PixaAlertDialog(
    modifier: Modifier = Modifier,
    dialogTitle: String? = null,
    onDismissRequest: () -> Unit = {},
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null,
    @StringRes positiveTextId: Int =
        if (onNegativeClick != null)
            R.string.yes
        else
            R.string.ok,
    @StringRes negativeTextId: Int =
        if (onPositiveClick != null)
            R.string.no
        else
            R.string.ok
) {
    PixaAlertDialog(modifier, dialogTitle, onDismissRequest) {
        onNegativeClick?.let {
            PixaButton(
                onClick = onNegativeClick,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(weight = 0.5f, fill = true),
            ) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = stringResource(id = negativeTextId),
                )
            }
        }
        onPositiveClick?.let {
            PixaButton(
                onClick = onPositiveClick,
                modifier = Modifier
                    .weight(weight = 0.5f, fill = true)
                    .requiredWidthIn(max = 160.dp),
                content = {
                    Text(
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center,
                        text = stringResource(id = positiveTextId),
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PixaAlertDialog(
    modifier: Modifier = Modifier,
    dialogTitle: String? = null,
    onDismissRequest: () -> Unit = {},
    bottomRow: @Composable RowScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        content = {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = modifier.padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    dialogTitle?.let {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            text = it,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(bottom = 24.dp))
                    Row(content = bottomRow)
                    Spacer(modifier = Modifier.padding(bottom = 16.dp))
                }
            }
        }
    )
}