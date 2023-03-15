package com.todoapp.feature_note.presentation.add_edit_notes

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.todoapp.R
import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.presentation.add_edit_notes.components.TransparentHintTextField
import com.todoapp.feature_note.presentation.notes.NoteEvents
import com.todoapp.feature_note.presentation.notes.components.NoteItem
import com.todoapp.feature_note.presentation.util.Screens
import com.todoapp.ui.theme.ToDoAppTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    //val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }
    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (noteColor != -1) noteColor else viewModel.noteColor.value)
        )
    }
    val scope = rememberCoroutineScope()
/*
* observing the events
* */
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.msg
                    )
                }

                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = stringResource(id = R.string.save_note))
            }
        }, //scaffoldState = scaffoldState
        snackbarHost = { SnackbarHost(snackbarHostState) },
    content= {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(ToDoAppTheme.paddings.defaultPadding)
        ) {
            LazyRow {
                items(Note.notesColors) {color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .padding(start = ToDoAppTheme.paddings.padding_3)
                            .size(ToDoAppTheme.paddings.padding_50)
                            .shadow(ToDoAppTheme.paddings.padding_15, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = ToDoAppTheme.paddings.padding_3,
                                color = if (viewModel.noteColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                    Spacer(modifier = Modifier.width(ToDoAppTheme.paddings.defaultPadding))
                }
            }
            /**===================**/
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(ToDoAppTheme.paddings.smallPadding),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {

//                Note.notesColors.forEach { color ->
//                    val colorInt = color.toArgb()
//                    Box(
//                        modifier = Modifier
//                            .size(ToDoAppTheme.paddings.padding_50)
//                            .shadow(ToDoAppTheme.paddings.padding_15, CircleShape)
//                            .clip(CircleShape)
//                            .background(color)
//                            .border(
//                                width = ToDoAppTheme.paddings.padding_3,
//                                color = if (viewModel.noteColor.value == colorInt) {
//                                    Color.Black
//                                } else Color.Transparent,
//                                shape = CircleShape
//                            )
//                            .clickable {
//                                scope.launch {
//                                    noteBackgroundAnimatable.animateTo(
//                                        targetValue = Color(colorInt),
//                                        animationSpec = tween(
//                                            durationMillis = 500
//                                        )
//                                    )
//                                }
//                                viewModel.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
//                            }
//                    )
//                }
         //   }
            Spacer(modifier = Modifier.height(ToDoAppTheme.paddings.defaultPadding))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeFocusTitle(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = ToDoAppTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(ToDoAppTheme.paddings.defaultPadding))
            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeFocusContent(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = ToDoAppTheme.typography.labelSmall,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
    )

}