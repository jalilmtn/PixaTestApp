package com.example.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixaTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = 1,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = PixaTextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor =
        if (!isError) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.error
    )
) {
    OutlinedTextField(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        ),
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = textStyle,
        maxLines = maxLines,
        placeholder = placeholder,
        singleLine = maxLines == 1,
    )
}

object PixaTextFieldDefaults {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun outlinedTextFieldColors(
        textColor: Color = MaterialTheme.colorScheme.onSurface,
        focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor: Color = MaterialTheme.colorScheme.onSurface,
        disabledTextColor: Color = MaterialTheme.colorScheme.onSurface,
    ) = TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        disabledTextColor = disabledTextColor
    )
}

@Composable
fun SearchLeadingIcon() {
    Icon(
        imageVector = Icons.Filled.Search,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSurface,
    )
}

@Composable
fun ClearTextIcon(onClearClick: () -> Unit) {
    IconButton(
        onClick = {
            onClearClick.invoke()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun PlaceholderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontStyle = FontStyle.Italic
    )
}
