package com.todoapp.feature_note.domain.usecases


import com.google.common.truth.Truth.assertThat
import com.todoapp.feature_note.data.repository.FakeNoteRepository
import com.todoapp.feature_note.domain.models.Note
import com.todoapp.feature_note.domain.util.NoteOrder
import com.todoapp.feature_note.domain.util.OrderType
import com.todoapp.ui.theme.BabyBlue
import com.todoapp.ui.theme.Green
import com.todoapp.ui.theme.LightBlue
import com.todoapp.ui.theme.LightGreen
import com.todoapp.ui.theme.Pink40
import com.todoapp.ui.theme.Pink80
import com.todoapp.ui.theme.Raspberry
import com.todoapp.ui.theme.RedOrange
import com.todoapp.ui.theme.RedPink
import com.todoapp.ui.theme.Yellow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Unit Test for--> Order functionality
 *
 * */
class GetNotesUseCaseTest {
    private lateinit var getNotes: GetNotesUseCase
    private lateinit var fakeNoteRepository: FakeNoteRepository

    /**
     * the setup function runs before every single test case
     * to initialize some objects
     * */
    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotesUseCase(fakeNoteRepository)

        /**----------populate list with some notes-----------**/
        val notesColors= listOf(
            RedOrange, RedPink,
            BabyBlue,
            Pink40,
            Pink80, LightGreen,
            LightBlue,
            Raspberry,
            Yellow,
            Green
        )

        var timeStampFake=System.currentTimeMillis()
        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, noteText ->
            notesToInsert.add(
                Note(
                    title = noteText.toString(),
                    content = noteText.toString() + "content",
                    timestamp = (timeStampFake+index),
                    color = index
                    //(notesColors.get(Random.nextInt(notesColors.size))).hashCode().toInt()
                )
            )
        }

        /**----------to get random order to rearrange--------**/
        notesToInsert.shuffle()

        /**---------Note list insert into repository----------**/
        runBlocking {
            notesToInsert.forEach {
                fakeNoteRepository.insertNote(it)
            }

        }

    }

    /**
    To get Notes by title ascending order
     * */
    @Test
    fun order_notes_by_title_ascending_correct() = runBlocking {
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    /**
    To get Notes by title descending order
     * */
    @Test
    fun order_notes_by_title_descending_correct() = runBlocking {
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }

    /**
    To get Notes by Date ascending order
     * */
    @Test
    fun order_notes_by_timeStamp_ascending_correct() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isLessThan(notes[i + 1].timestamp)
        }
    }

    /**
    To get Notes by Date descending order
     * */
    @Test
    fun order_notes_by_timeStamp_descending_correct() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].timestamp).isGreaterThan(notes[i + 1].timestamp)
        }
    }

    /**
    To get Notes by Color ascending order
     * */
    @Test
    fun order_notes_by_color_ascending_correct() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isLessThan(notes[i + 1].color)
        }
    }

    /**
    To get Notes by Color descending order
     * */
    @Test
    fun order_notes_by_color_descending_correct() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isGreaterThan(notes[i + 1].color)
        }
    }

}