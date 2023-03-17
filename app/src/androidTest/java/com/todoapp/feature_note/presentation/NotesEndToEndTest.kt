package com.todoapp.feature_note.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.common.truth.Truth.*
import com.todoapp.core.TestTags
import com.todoapp.di.AppModule
import com.todoapp.feature_note.presentation.add_edit_notes.AddEditNoteScreen
import com.todoapp.feature_note.presentation.notes.NotesScreen
import com.todoapp.feature_note.presentation.util.Screens
import com.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {
    /**-------Rule to inject dependencies------**/
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /**-------compose test rule------**/
    @get:Rule(order = 1)
    val composableRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        composableRule.setContent {
            ToDoAppTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.NotesScreen.route
                )
                {
                    composable(route = Screens.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }
                    composable(route = Screens.AddEditNoteScreen.route +
                            "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "noteColor"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(
                            navController = navController,
                            noteColor = color
                        )
                    }
                }
            }

        }


    }

    /**
     * Test For:
     * --Tap on Add floating Button
     * --enter title
     * --enter content
     * --tap on Save Floating Button -if it works it will navigate back on home screen
     *
     *
     * **/
    @Test
    fun saveNote_editAfter() {
        composableRule.onNodeWithContentDescription("Add note").performClick()
        composableRule.onNodeWithTag(TestTags.TITLE_TEXT_FIElD)
            .performTextInput("test-title")
        composableRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIElD)
            .performTextInput("content-title")
        composableRule.onNodeWithContentDescription("Save-note").performClick()
/**---------------Check list has particular Note-------------**/
        composableRule.onNodeWithText("test-title").assertIsDisplayed()
        composableRule.onNodeWithText("test-title").performClick()
        /**---------------Navigate on ADD Edit Note Screen-------------**/
        composableRule.onNodeWithTag(TestTags.TITLE_TEXT_FIElD)
            .assertTextEquals("test-title")
        composableRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIElD)
            .assertTextEquals("content-title")
        /**---------------Edit Note Screen-------------**/
        composableRule.onNodeWithTag(TestTags.TITLE_TEXT_FIElD)
            .performTextInput("test-22title")
        composableRule.onNodeWithContentDescription("Save-note")
            .performClick()
        composableRule.onNodeWithTag(TestTags.TITLE_TEXT_FIElD)
            .assertTextEquals("test-22title")


    }
}