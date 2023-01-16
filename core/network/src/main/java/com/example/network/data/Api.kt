package com.example.network.data

import com.example.network.data.remote.dto.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {
    @GET("?image_type=photo")
    suspend fun getPixaImage(
        @QueryMap(encoded = true) queryMap: Map<String, String>,
    ): SearchImageResponse
}