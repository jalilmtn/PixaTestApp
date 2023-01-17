package com.example.network.repo

import com.example.network.data.remote.dto.SearchImageResponse

interface PixaApiRepo {
    suspend fun getImages(txt: String): SearchImageResponse
}