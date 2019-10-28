package com.devloop.notesapp.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.devloop.notesapp.Database.dao.NoteDao
import com.devloop.notesapp.Database.entities.Note

// This is a class to check whether to fetch data from API or local database

class NoteRepository(private val noteDao: NoteDao) {
    private val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    // wrapper for insert() and getAllNotes() and deleteAllNotes()

    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
        val noteDao = noteDao

        override fun doInBackground(vararg p0: Note?) {
            noteDao.insert(p0[0]!!)
        }
    }

    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg p0 : Unit?) {
            noteDao.deleteAllNotes()
        }
    }
}