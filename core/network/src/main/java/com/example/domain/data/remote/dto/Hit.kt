package com.example.domain.data.remote.dto

import com.example.domain.model.Image

data class Hit(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
)

fun Hit.toImage(): Image {
    return Image(
        id = id,
        comments = comments,
        downloads = downloads,
        largeImageURL = largeImageURL,
        likes = likes,
        previewHeight = previewHeight,
        previewURL = previewURL,
        tags = tags,
        user = user,
        views = views
    )
}