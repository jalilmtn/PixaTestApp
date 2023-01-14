package com.example.domain.model

data class Image(
    val id: Int,
    val comments: Int,
    val downloads: Int,
    val largeImageURL: String,
    val likes: Int,
    val previewHeight: Int,
    val previewURL: String,
    val tags: String,
    val user: String,
    val views: Int,
)