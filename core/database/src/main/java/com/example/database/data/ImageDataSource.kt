package com.example.database.data

import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow

interface ImageDataSource {
    suspend fun getImageById(id: Long): ImageEntitiy?
    fun getAllImages(): Flow<List<ImageEntitiy>>
    suspend fun insertImage(image: ImageEntitiy)
}