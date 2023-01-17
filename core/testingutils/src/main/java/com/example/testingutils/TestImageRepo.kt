package com.example.testingutils

import com.example.database.data.ImageDatabaseRepo
import imagedb.ImageEntitiy
import kotlinx.coroutines.flow.Flow

open class TestImageRepo : ImageDatabaseRepo {

    override suspend fun getImagesByTag(localTag: String): Flow<List<ImageEntitiy>> {
        TODO("Not yet implemented")
    }

    override suspend fun getImageById(id: Long): ImageEntitiy? {
        TODO("Not yet implemented")
    }

    override suspend fun insertImage(localTag: String, image: ImageEntitiy) {
        TODO("Not yet implemented")
    }
}