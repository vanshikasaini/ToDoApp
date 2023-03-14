package com.todoapp.di

import android.app.Application
import androidx.room.Room
import com.todoapp.feature_note.data.data_source.NoteDatabase
import com.todoapp.feature_note.data.repository.NoteRepoImpl
import com.todoapp.feature_note.domain.repo.NoteRepository
import com.todoapp.feature_note.domain.usecases.AddNoteUseCase
import com.todoapp.feature_note.domain.usecases.DeleteNoteUseCase
import com.todoapp.feature_note.domain.usecases.GetNotesUseCase
import com.todoapp.feature_note.domain.usecases.NoteUseCases
import com.todoapp.feature_note.domain.usecases.SingleNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
/*
* Provide database
* */
    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
    /*
    * provides NoteRepository
    * */
    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository{
        return  NoteRepoImpl(db.noteDao)
    }

    /*
    *provide - usecases
    * */

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository,app:Application):NoteUseCases{

        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository,app),
            getNote = SingleNoteUseCase(repository)
        )
    }
}