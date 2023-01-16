package com.example.network.usecase

import com.example.common.Resource
import com.example.database.data.ImageRepo
import com.example.network.data.remote.dto.toImage
import com.example.network.repo.PixaRepo
import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repo: PixaRepo,
    private val imageRepo: ImageRepo
) {
    operator fun invoke(txt: String): Flow<Resource<List<ImageEntitiy>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repo.getImages(txt)
            val images = result.hits.map { it.toImage() }
            images.forEach {
                imageRepo.insertImage(txt, it)
            }
            emit(Resource.Success(images))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error("Something went wrong, try again later."))
        }
    }
}