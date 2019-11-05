package com.devloop.notesapp.Database

import com.devloop.notesapp.Database.entities.Note

class DataGenerator {
    companion object {
        fun getNotes(): Array<Note>{
            return arrayOf(
                Note("Title 1", "description 1"),
                Note("Title 2", "description 2"),
                Note("Title 3", "description 3")
            )
        }
    }
}