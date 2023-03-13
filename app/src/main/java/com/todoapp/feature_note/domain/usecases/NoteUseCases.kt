package com.todoapp.feature_note.domain.usecases

data class NoteUseCases(val getNotes:GetNotesUseCase,
                        val deleteNote:DeleteNoteUseCase,
                        val addNote:AddNoteUseCase,
                        val getNote:SingleNoteUseCase)
