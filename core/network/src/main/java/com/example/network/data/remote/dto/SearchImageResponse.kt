package com.example.network.data.remote.dto

data class SearchImageResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)