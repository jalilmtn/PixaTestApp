package com.example.database.data

import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val imageDataSource: ImageDataSource,
) : ImageRepo {
    override suspend fun getImages(): Flow<List<ImageEntitiy>> {
        return imageDataSource.getAllImages()
    }

    override suspend fun getImageById(id: Long): ImageEntitiy? {
        return imageDataSource.getImageById(id)
    }

    override suspend fun insertImage(image: ImageEntitiy) {
        imageDataSource.insertImage(image)
    }
}