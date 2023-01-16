package com.example.detail

import androidx.lifecycle.SavedStateHandle
import com.example.testingutils.MainDispatcherRule
import com.example.testingutils.TestImageRepo
import com.google.common.truth.Truth.assertThat
import imagedb.ImageEntitiy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

internal class DetailViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    lateinit var viewModel: DetailsViewModel

    private val testImageRepo = object : TestImageRepo() {
        override suspend fun getImageById(id: Long): ImageEntitiy? {
            return if (id == 1L)
                ImageEntitiy(
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
            else null
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getImageByWrongId() = runTest(UnconfinedTestDispatcher()) {
        viewModel = DetailsViewModel(
            testImageRepo,
            SavedStateHandle(mapOf("imageId" to 2L))
        )
        assertThat(viewModel.viewState.value).isNull()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getImageById() = runTest(UnconfinedTestDispatcher()) {
        viewModel = DetailsViewModel(
            testImageRepo,
            SavedStateHandle(mapOf("imageId" to 1L))
        )
        assertThat(viewModel.viewState.value?.id).isEqualTo(1L)
    }
}