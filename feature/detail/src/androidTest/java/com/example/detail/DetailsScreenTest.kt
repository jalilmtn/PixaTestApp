package com.example.detail

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.common.Constants.TOPIC_LOADING
import imagedb.ImageEntitiy
import org.junit.Rule
import org.junit.Test

//TODO: we need to implement test for other parts too
class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @SuppressLint("UnrememberedMutableState")
    @Test
    fun progressDialog_OnLoading_Is_exists() {
        composeTestRule.setContent {
            DetailScreen(
                ImageEntitiy(
                    id = 1,
                    user = "test",
                    previewHeight = 1,
                    localTag = "test",
                    tags = "apple, dog",
                    previewURL = "url",
                    likes = 123,
                    largeImageURL = "https://cdn.pixabay.com/photo/2017/06/02/18/24/watermelon-2367029_150.jpg",
                    downloads = 125,
                    comments = 126,
                    previewWidth = 2
                )
            )
        }
        composeTestRule
            .onNodeWithContentDescription(TOPIC_LOADING)
            .assertIsDisplayed()
    }
}