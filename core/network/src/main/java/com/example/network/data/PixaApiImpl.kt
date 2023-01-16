package com.example.network.data

import com.example.common.Constants
import com.example.network.data.remote.dto.SearchImageResponse
import com.example.network.repo.PixaRepo
import javax.inject.Inject


class PixaApiImpl @Inject constructor(
    private val api: Api
) : PixaRepo {
    override suspend fun getImages(txt: String): SearchImageResponse {
        val data = HashMap<String, String>()
        data["key"] = Constants.KEY
        data["q"] = txt
        return api.getPixaImage(data)
    }
}