package com.example.domain.data

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/?image_type=photo")
    suspend fun getPixaImage(
        @Query("q") searchText: String,
        @Query("key") key: String
    ): String
}