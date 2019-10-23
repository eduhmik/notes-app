import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

// This is a class to check whether to fetch data from API or local database

class NoteRepository(application: Application) {
    private var noteDao: NoteDao

    private var allNotes: LiveData<List<Note>>

    // wrapper for insert() and getAllNotes() and deleteAllNotes()

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }


    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(noteDao).execute()
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