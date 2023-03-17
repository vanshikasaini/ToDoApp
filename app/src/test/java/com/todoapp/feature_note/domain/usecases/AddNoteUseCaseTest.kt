package com.todoapp.feature_note.domain.usecases

//import androidx.test.platform.app.InstrumentationRegistry
import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.todoapp.feature_note.data.repository.FakeNoteRepository
import com.todoapp.feature_note.domain.models.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit Test for-->Insert Note
 *
 * */
//@RunWith(AndroidJUnit4::class)
class AddNoteUseCaseTest{
    private lateinit var note: Note
    private lateinit var addNote: AddNoteUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var context: Context
// val context_= ApplicationProvider.getApplicationContext<Context>()
    /**
     * the setup function runs before every single test case
     * to initialize some objects
     * */
    @Before
    fun setup(){

        fakeNoteRepository=FakeNoteRepository()
        addNote=AddNoteUseCase(fakeNoteRepository)


    }
    /**-----Validate before Add Note---**/
    @Test
    fun note_title_validation_done()  {
        note=  Note(title = "",
            content = "content",
            timestamp =1,
            color = 1)
        runBlocking {
            try {
                var value = addNote(note = note)

            }catch (e:Exception){
                assertThat(e).hasCauseThat()
            }
        }
    }
    /**-----Validate before Add Note---**/
    @Test
    fun note_content_validation_done()  {
        note=  Note(title = "title",
            content = "",
            timestamp =1,
            color = 1)
        runBlocking {
            try {
                var value = addNote(note = note)

            }catch (e:Exception){
                assertThat(e).hasCauseThat()
            }
        }
    }
    /**-----Validate before Add Note---**/
    @Test
    fun note_insert_done()  {
        note=  Note(title = "title",
            content = "content",
            timestamp =1,
            color = 1)
        runBlocking {
            try {
                var value = addNote(note = note)
                fakeNoteRepository.insertNote(note)
                val result=fakeNoteRepository.getNotes().first().isNotEmpty()
                assertThat(result).isTrue()
            }catch (e:Exception){
                assertThat(e).hasCauseThat()
            }
        }

    }
}