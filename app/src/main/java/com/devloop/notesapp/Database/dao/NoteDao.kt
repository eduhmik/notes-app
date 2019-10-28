package com.devloop.notesapp.Database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devloop.notesapp.Database.entities.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Query("Delete FROM notes_table")
    fun deleteAllNotes()

    @Query("Select * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>
}