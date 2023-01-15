package com.example.domain.data

import com.example.common.Constants
import com.example.domain.data.remote.dto.SearchImageResponse
import com.example.domain.repo.PixaRepo
import javax.inject.Inject

class PixaApiImpl @Inject constructor(
    private val api: Api
) : PixaRepo {
    override suspend fun getImages(txt: String): SearchImageResponse {
        return api.getPixaImage(txt, Constants.KEY)
    }
}