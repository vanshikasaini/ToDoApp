package com.todoapp.feature_note.domain.usecases

import com.todoapp.feature_note.data.repository.FakeNoteRepository
import com.todoapp.feature_note.data.repository.NoteRepoImpl
import com.todoapp.feature_note.domain.models.Note
import org.junit.Assert.*
import org.junit.Before

/*
* Unit Test for--> Order functionality
*
* */
class GetNotesUseCaseTest {
    private lateinit var getNotes: GetNotesUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    /*
    * the setup function runs before every single test case
    * to initialize some objects
    * */
    @Before
    fun setUp() {
        fakeNoteRepository= FakeNoteRepository()
        getNotes = GetNotesUseCase(fakeNoteRepository)

       /**----------populate list with some notes-----------**/
        val notesToInsert= mutableListOf<Note>()
    }


}