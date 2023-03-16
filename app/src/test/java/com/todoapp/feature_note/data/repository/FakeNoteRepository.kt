package com.todoapp.feature_note.data.repository

import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.repo.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
*Created Repo for Test Case - to get the same data as real repository
* Simulate actual database with simple List
* */
class FakeNoteRepository : NoteRepository {

    /**-----------List of Notes-----------**/
    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow { emit(notes) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }
}