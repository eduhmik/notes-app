package com.devloop.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devloop.notesapp.Database.entities.Note
import com.devloop.notesapp.repository.NoteRepository


class NoteViewModel(private var repository: NoteRepository) : ViewModel() {

    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}