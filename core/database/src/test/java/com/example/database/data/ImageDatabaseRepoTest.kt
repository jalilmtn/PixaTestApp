package com.example.database.data

import com.example.database.InMemoryDatabaseDriverFactory
import com.google.common.truth.Truth.assertThat
import imagedb.ImageEntitiy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ImageDatabaseRepoTest {
    lateinit var imageDatabaseRepo: ImageDatabaseRepo

    @Before
    fun setup() {
        val database = InMemoryDatabaseDriverFactory.createDatabase()
        imageDatabaseRepo = ImageDatabaseRepoImpl(database.imageEntitiyQueries)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertImageTest() = runTest(UnconfinedTestDispatcher()) {
        val imageId = 1L
        val testImage = ImageEntitiy(
            id = imageId,
            user = "test",
            previewHeight = 1,
            localTag = "test",
            tags = "apple, dog",
            previewURL = "url",
            likes = 123,
            largeImageURL = "l url",
            downloads = 125,
            comments = 126,
            previewWidth = 2
        )
        imageDatabaseRepo.insertImage(
            localTag = "test",
            testImage
        )
        val image = imageDatabaseRepo.getImageById(imageId)
        assertThat(image?.id).isEqualTo(imageId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllImageByLocalTagTest() = runTest(UnconfinedTestDispatcher()) {
        val testImage1 = ImageEntitiy(
            id = 1,
            user = "test",
            previewHeight = 1,
            localTag = "test",
            tags = "apple, dog",
            previewURL = "url",
            likes = 123,
            largeImageURL = "l url",
            downloads = 125,
            comments = 126,
            previewWidth = 2
        )
        val testImage2 = ImageEntitiy(
            id = 2,
            user = "test",
            previewHeight = 1,
            localTag = "ok",
            tags = "apple, dog",
            previewURL = "url",
            likes = 123,
            largeImageURL = "l url",
            downloads = 125,
            comments = 126,
            previewWidth = 2
        )
        val testImage3 = ImageEntitiy(
            id = 3,
            user = "test",
            previewHeight = 1,
            localTag = "Good",
            tags = "apple, dog",
            previewURL = "url",
            likes = 123,
            largeImageURL = "l url",
            downloads = 125,
            comments = 126,
            previewWidth = 2
        )
        imageDatabaseRepo.insertImage(
            localTag = "ok",
            testImage1
        )
        imageDatabaseRepo.insertImage(
            localTag = "ok",
            testImage2
        )
        imageDatabaseRepo.insertImage(
            localTag = "ok",
            testImage3
        )
        val images = imageDatabaseRepo.getImagesByTag("ok")
        images.first().forEach {
                assertThat(it.localTag).isEqualTo("ok")
            }
    }

}