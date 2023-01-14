package com.example.common
import android.content.Context
import android.graphics.drawable.Drawable
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size

val THUMBNAIL_SIZE = Size(250, 250)

fun coilImageRequest(
    context: Context,
    data: Any?,
    placeHolder: Int = R.drawable.placeholder,
    placeHolderDrawable: Drawable? = null,
    error: Int = R.drawable.placeholder,
    size: Size = Size.ORIGINAL,
    retryHash: Int? = null,
    memoryCacheKey: String? = null,
    onStart: (request: ImageRequest) -> Unit = {},
    onCancel: (request: ImageRequest) -> Unit = {},
    onError: (request: ImageRequest, result: ErrorResult) -> Unit = { _, _ -> },
    onSuccess: (request: ImageRequest, result: SuccessResult) -> Unit = { _, _ -> }
): ImageRequest {
    val imageRequestBuilder = ImageRequest.Builder(context)
        .size(size)
        .placeholder(placeHolder)
        .placeholder(placeHolderDrawable)
        .error(error)
        .setParameter("retry_hash", retryHash)
        .data(data = data)
        .memoryCacheKey(memoryCacheKey)
        .listener(onStart, onCancel, onError, onSuccess)
    if (placeHolderDrawable == null) {
        imageRequestBuilder.placeholder(placeHolder)
    } else {
        imageRequestBuilder.placeholder(placeHolderDrawable)
    }
    return imageRequestBuilder.build()
}
