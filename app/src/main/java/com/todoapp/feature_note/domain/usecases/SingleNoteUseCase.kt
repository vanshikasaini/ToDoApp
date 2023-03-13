package com.todoapp.feature_note.domain.usecases

import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.repo.NoteRepository

class SingleNoteUseCase(   private val repository: NoteRepository
) {

    suspend operator fun invoke(id:Int): Note?{
        return repository.getNoteById(id)
    }
}