package com.example.database.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import imagedb.ImageEntitiy
import imagedb.ImageEntitiyQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageDatabaseRepoImpl @Inject constructor(
    private val imageDataSource: ImageEntitiyQueries,
) : ImageDatabaseRepo {
    override suspend fun getImagesByTag(localTag: String): Flow<List<ImageEntitiy>> {
        return withContext(Dispatchers.IO) {
            imageDataSource.getImagesByTag(localTag).asFlow().mapToList()
        }
    }

    override suspend fun getImageById(id: Long): ImageEntitiy? {
        return withContext(Dispatchers.IO) { imageDataSource.getImageById(id).executeAsOneOrNull() }
    }

    override suspend fun insertImage(localTag: String, image: ImageEntitiy) {
        withContext(Dispatchers.IO) {
            with(image) {
                imageDataSource.insertImage(
                    id = id,
                    user = user,
                    largeImageURL = largeImageURL,
                    comments = comments,
                    downloads = downloads,
                    likes = likes,
                    previewURL = previewURL,
                    tags = tags,
                    localTag = localTag,
                    previewHeight = previewHeight,
                    previewWidth = previewWidth
                )
            }
        }
    }
}