package com.devloop.notesapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devloop.notesapp.adapter.NoteAdapter
import com.devloop.notesapp.Database.entities.Note
import com.devloop.notesapp.viewmodel.NoteViewModel
import com.devloop.notesapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val ADD_NOTE_REQUEST = 1
    private lateinit var noteViewModel : NoteViewModel
    private val adapter = NoteAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                ADD_NOTE_REQUEST
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this,
            Observer<List<Note>> { t -> adapter.setNotes(t!!) })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && requestCode == Activity.RESULT_OK) {
            val newNote = Note(
                data!!.getStringExtra(AddNoteActivity.EXTRA_TITLE),
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            )
            noteViewModel.insert(newNote)

            Toast.makeText(this,"Note Saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
