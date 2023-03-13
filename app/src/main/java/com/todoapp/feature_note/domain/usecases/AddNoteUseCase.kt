package com.todoapp.feature_note.domain.usecases

import com.todoapp.feature_note.domain.models.InvalidNoteException
import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.repo.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        /*
        * Validate or business logic
        * */
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty")
        }

        repository.insertNote(note)
    }
}
