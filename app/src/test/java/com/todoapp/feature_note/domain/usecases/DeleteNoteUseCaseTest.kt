package com.todoapp.feature_note.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.todoapp.feature_note.data.repository.FakeNoteRepository
import com.todoapp.feature_note.domain.models.Note
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest{
    private lateinit var deleteNote: DeleteNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun SetUp(){
        fakeNoteRepository=FakeNoteRepository()
        deleteNote=DeleteNoteUseCase(fakeNoteRepository)

    }
    @Test
    fun note_delete_done() = runBlocking {
        val  note=  Note(title = "title",
            content = "",
            timestamp =1,
            color = 1)
       val testResult= deleteNote(note)
        assertThat(testResult).isNotNull()

    }
}