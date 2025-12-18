package com.example.fastnotes.data.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.fastnotes.data.database.NoteDao;
import com.example.fastnotes.data.database.NotesDatabase;
import com.example.fastnotes.data.model.Note;
import java.util.List;

public class NotesRepository2 {
    // MVVM
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NotesRepository2(Application application) {
        NotesDatabase db = NotesDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    // AsyncTask для фоновой работы
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao asyncTaskDao;

        InsertNoteAsyncTask(NoteDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.insert(notes[0]);
            return null;
        }
    }
}