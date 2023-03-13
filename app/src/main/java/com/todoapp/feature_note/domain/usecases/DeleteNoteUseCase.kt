package com.todoapp.feature_note.domain.usecases

import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.repo.NoteRepository

class DeleteNoteUseCase(private  val repo:NoteRepository) {

    suspend operator fun invoke(note: Note){
        return repo.deleteNote(note)
    }
}