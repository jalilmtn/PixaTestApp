package com.example.database.data

import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val imageDataSource: ImageDataSource,
) : ImageRepo {
    override suspend fun getImagesByTag(localTag: String): Flow<List<ImageEntitiy>> {
        return imageDataSource.getImagesByTag(localTag)
    }

    override suspend fun getImageById(id: Long): ImageEntitiy? {
        return imageDataSource.getImageById(id)
    }

    override suspend fun insertImage(localTag: String, image: ImageEntitiy) {
        imageDataSource.insertImage(localTag, image)
    }
}