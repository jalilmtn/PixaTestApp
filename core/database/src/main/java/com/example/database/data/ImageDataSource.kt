package com.example.database.data

import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow

interface ImageDataSource {
    suspend fun getImageById(id: Long): ImageEntitiy?
    fun getImagesByTag(localTag: String,): Flow<List<ImageEntitiy>>
    suspend fun insertImage(localTag: String, image: ImageEntitiy)
}