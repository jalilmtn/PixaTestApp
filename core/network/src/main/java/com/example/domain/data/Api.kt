package com.example.domain.data

import com.example.domain.data.remote.dto.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {
    @GET("?image_type=photo")
    suspend fun getPixaImage(
        @QueryMap(encoded = true) queryMap: Map<String, String>,
    ): SearchImageResponse
}