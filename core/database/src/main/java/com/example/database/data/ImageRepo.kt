package com.example.database.data

import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow

interface ImageRepo {
    suspend fun getImagesByTag(localTag: String): Flow<List<ImageEntitiy>>
    suspend fun getImageById(id:Long): ImageEntitiy?
    suspend fun insertImage(localTag: String, image: ImageEntitiy)
}