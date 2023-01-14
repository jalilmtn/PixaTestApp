package com.example.domain.usecase

import com.example.common.Resource
import com.example.domain.data.remote.dto.toImage
import com.example.domain.model.Image
import com.example.domain.repo.PixaRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val repo: PixaRepo) {
    operator fun invoke(): Flow<Resource<List<Image>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repo.getImages()
            emit(Resource.Success(result.hits.map { it.toImage() }))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong, try again later."))
        }
    }
}