package com.example.feed

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.common.Constants.TOPIC_LOADING
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Rule
import org.junit.Test

//TODO: we need to implement test for other parts too
class FeedScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @SuppressLint("UnrememberedMutableState")
    @Test
    fun progressBar_OnLoading_Is_exists() {
        composeTestRule.setContent {
                FeedScreen(
                    searchTxt = mutableStateOf(""),
                    setName = {},
                    state = ViewState(
                        emptyFlow(),
                        isLoading = true,
                        error = ""
                    ),
                    onImageClick = {},
                    imageListState = mutableStateOf(emptyList())
                )
        }
        composeTestRule.onNodeWithContentDescription(TOPIC_LOADING).assertIsDisplayed()
    }
}