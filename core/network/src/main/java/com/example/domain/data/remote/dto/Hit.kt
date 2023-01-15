package com.example.domain.data.remote.dto

import imagedb.ImageEntitiy


data class Hit(
    val collections: Int,
    val comments: Long,
    val downloads: Long,
    val id: Long,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Long,
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

fun Hit.toImage(): ImageEntitiy {
    return ImageEntitiy(
        id = id,
        comments = comments,
        downloads = downloads,
        largeImageURL = largeImageURL,
        likes = likes,
        previewURL = previewURL,
        tags = tags,
        user = user,
        localTag = ""
    )
}