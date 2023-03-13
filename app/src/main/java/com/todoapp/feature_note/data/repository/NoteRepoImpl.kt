package com.todoapp.feature_note.data.repository

import com.todoapp.feature_note.data.data_source.NoteDao
import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.repo.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(private val dao:NoteDao):NoteRepository{
    override fun getNotes(): Flow<List<Note>> {
       return  dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return  dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return  dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return  dao.deleteNote(note)
    }
}