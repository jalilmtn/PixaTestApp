package com.example.database.data

import com.example.database.InMemoryDatabaseDriverFactory
import com.google.common.truth.Truth.assertThat
import imagedb.ImageEntitiyQueries
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ImageRepoTest {
    //TODO: its better to use Image repo impl here
    private lateinit var queries: ImageEntitiyQueries

    @Before
    fun setup() {
        val database = InMemoryDatabaseDriverFactory.createDatabase()
        queries = database.imageEntitiyQueries
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertImageTest() = runTest(UnconfinedTestDispatcher()) {
        val imageId = 1L
        queries.insertImage(
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
        val image = queries.getImageById(imageId).executeAsOne()
        assertThat(image.id).isEqualTo(imageId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllImageByLocalTagTest() = runTest(UnconfinedTestDispatcher()) {
        queries.insertImage(
            id = 1,
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
        queries.insertImage(
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
        queries.insertImage(
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

        val images = queries.getImagesByTag("ok").executeAsList()
        images.forEach {
            assertThat(it.localTag).isEqualTo("ok")
        }
    }

}