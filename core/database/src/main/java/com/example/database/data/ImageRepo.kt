package com.example.database.data

import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow

interface ImageRepo {
    suspend fun getImages(): Flow<List<ImageEntitiy>>
    suspend fun getImageById(id:Long): ImageEntitiy?
    suspend fun insertImage(image: ImageEntitiy)
}