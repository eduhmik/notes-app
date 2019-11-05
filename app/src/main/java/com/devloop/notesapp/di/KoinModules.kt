package com.devloop.notesapp.di

import com.devloop.notesapp.viewmodel.NoteViewModel
import com.devloop.notesapp.Database.NoteDatabase
import com.devloop.notesapp.di.Properties.ROOM_DB_NAME
import com.devloop.notesapp.repository.NoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module



val dbModule = module {
    single { NoteDatabase.getInstance(androidContext(), ROOM_DB_NAME)}
    single { get<NoteDatabase>().noteDao() }
}

val repositoryModule = module {
    single { NoteRepository(get()) }
}

val uiModule = module {
    viewModel { NoteViewModel(get()) }
}

object Properties {
    const val ROOM_DB_NAME = "notes_database"
}

val appModules = listOf(dbModule, repositoryModule, uiModule)