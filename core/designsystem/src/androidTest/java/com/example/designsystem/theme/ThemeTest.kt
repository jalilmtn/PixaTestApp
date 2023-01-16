package com.example.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ThemeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun darkThemeFalse() {
        composeTestRule.setContent {
            PixaTheme(
                darkTheme = false
            ) {
                val colorScheme = LightColorPalette
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
            }
        }
    }

    @Test
    fun darkThemeTrue() {
        composeTestRule.setContent {
            PixaTheme(
                darkTheme = true
            ) {
                val colorScheme = DarkColorPalette
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
            }
        }
    }

    private fun assertColorSchemesEqual(
        expectedColorScheme: ColorScheme,
        actualColorScheme: ColorScheme
    ) {
        assertEquals(expectedColorScheme.primary, actualColorScheme.primary)
        assertEquals(expectedColorScheme.background, actualColorScheme.background)
        assertEquals(expectedColorScheme.onBackground, actualColorScheme.onBackground)
        assertEquals(expectedColorScheme.onPrimary, actualColorScheme.onPrimary)
    }
}
