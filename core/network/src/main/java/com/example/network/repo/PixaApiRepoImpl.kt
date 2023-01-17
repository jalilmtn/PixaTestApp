package com.example.network.repo

import com.example.common.Constants
import com.example.network.data.Api
import com.example.network.data.remote.dto.SearchImageResponse
import javax.inject.Inject


class PixaApiRepoImpl @Inject constructor(
    private val api: Api
) : PixaApiRepo {
    override suspend fun getImages(txt: String): SearchImageResponse {
        val data = HashMap<String, String>()
        data["key"] = Constants.KEY
        data["q"] = txt
        return api.getPixaImage(data)
    }
}