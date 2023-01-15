package com.example.database.data

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import imagedb.ImageDatabase
import imagedb.ImageEntitiy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ImageDataSourceImpl(
    db: ImageDatabase
) : ImageDataSource {
    private val queries = db.imageEntitiyQueries

    //TODO inject dispatcher
    override suspend fun getImageById(id: Long): ImageEntitiy? {
        return withContext(Dispatchers.IO) {
            queries.getImageById(id).executeAsOneOrNull()
        }
    }

    override fun getImagesByTag(localTag: String): Flow<List<ImageEntitiy>> {
        return queries.getImagesByTag(localTag).asFlow().mapToList()
    }

    override suspend fun insertImage(localTag:String, image: ImageEntitiy) {
        withContext(Dispatchers.IO) {
            with(image) {
                queries.insertImage(
                    id = id,
                    user = user,
                    largeImageURL = largeImageURL,
                    comments = comments,
                    downloads = downloads,
                    likes = likes,
                    previewURL = previewURL,
                    tags = tags,
                    localTag = localTag
                )
            }

        }
    }

}