package com.example.network.repo

import com.example.network.data.remote.dto.SearchImageResponse

interface PixaRepo {
    suspend fun getImages(txt: String): SearchImageResponse
}