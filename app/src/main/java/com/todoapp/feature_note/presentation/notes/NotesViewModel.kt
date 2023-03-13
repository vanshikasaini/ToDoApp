package com.todoapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.usecases.NoteUseCases
import com.todoapp.feature_note.domain.util.NoteOrder
import com.todoapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUsesCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentDeletedNote: Note? = null


    private  var getNoteJob: Job?=null
    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }
    /*
    * call from UI
    * */
    fun onEVent(event: NoteEvents) {
        when (event) {

            is NoteEvents.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderTye== event.noteOrder.orderTye){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    /*
                    * we override invoke so called with class object call
                    * */
                    noteUsesCases.deleteNote(event.note)
                    recentDeletedNote = event.note
                }

            }

            is NoteEvents.RestoreNote -> {
                viewModelScope.launch {

                    noteUsesCases.addNote(recentDeletedNote ?: return@launch)

                    recentDeletedNote = null
                }
            }

            is NoteEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }

    }

    private fun getNotes(noteOrder: NoteOrder){
/*
* everytime called getNotes fun- get new flow a new instance of that flow
* so when we call everytime we should cancel the old coroutine
* */
        getNoteJob?.cancel()

        getNoteJob=noteUsesCases.getNotes().onEach {notes->
            _state.value=state.value.copy(notes =notes,
                noteOrder=noteOrder)
        }.launchIn(viewModelScope)
    }
}