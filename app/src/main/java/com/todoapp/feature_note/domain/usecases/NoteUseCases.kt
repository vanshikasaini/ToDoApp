package com.todoapp.feature_note.domain.usecases

data class NoteUseCases(val getNotes:GetNoesUseCase,
    val deleteNote:DeleteNoteUseCase,
    val addNote:AddNoteUseCase)
