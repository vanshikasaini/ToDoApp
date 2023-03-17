package com.todoapp.feature_note.presentation.notes

import android.content.Context
import com.google.common.truth.Truth.*
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.todoapp.R
import com.todoapp.core.TestTags
import com.todoapp.di.AppModule
import com.todoapp.feature_note.presentation.MainActivity
import com.todoapp.feature_note.presentation.util.Screens
import com.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
//import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreenKtTest {
    /**-------Rule to inject dependencies------**/
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /**-------compose test rule------**/
    @get:Rule(order = 1)
    val compsableRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        compsableRule.setContent {

            val navController = rememberNavController()
            ToDoAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screens.NotesScreen.route
                ) {
                    composable(Screens.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }
                }

            }
        }
    }

    @Test
    fun clickToggleOrderSection_isVisible() {
//        val context = InstrumentationRegistry.getInstrumentation()
//            .targetContext//
//        val con= ApplicationProvider.getApplicationContext<Context>()
//        con.applicationContext.resources.getString(R.string.sort)
        compsableRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        compsableRule.onNodeWithContentDescription("Sort").performClick()
        compsableRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()

    }
}