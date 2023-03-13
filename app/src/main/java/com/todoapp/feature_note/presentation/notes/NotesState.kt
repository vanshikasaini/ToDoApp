package com.todoapp.feature_note.presentation.notes

import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.util.NoteOrder
import com.todoapp.feature_note.domain.util.OrderType

data class NotesState(

    val notes:List<Note> = emptyList(),
    val noteOrder : NoteOrder=NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean=false
)
