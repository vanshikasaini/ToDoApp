package com.todoapp.feature_note.domain.usecases

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.todoapp.R
import com.todoapp.feature_note.domain.models.InvalidNoteException
import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.repo.NoteRepository
import dagger.hilt.android.qualifiers.ApplicationContext

class AddNoteUseCase(
    private val repository: NoteRepository
) {//,private val context: Context
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        /*
        * Validate or business logic
        * */
        if (note.title.isBlank()) {
           // throw InvalidNoteException("context.getString(R.string.title_msg)")
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.content.isBlank()) {
          //  throw InvalidNoteException("context.getString(R.string.content_msg)")
            throw InvalidNoteException("The content of the note can't be empty")
        }

        repository.insertNote(note)
    }
}
