package com.example.domain.repo

import com.example.domain.data.remote.dto.SearchImageResponse

interface PixaRepo {
    suspend fun getImages(txt: String): SearchImageResponse
}