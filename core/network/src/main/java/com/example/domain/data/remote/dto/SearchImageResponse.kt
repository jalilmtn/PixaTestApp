package com.example.domain.data.remote.dto

data class SearchImageResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)