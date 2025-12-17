package com.example.fastnotes.data.repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.InvalidationTracker;

import com.example.fastnotes.data.database.NoteDao;
import com.example.fastnotes.data.database.NotesDatabase;
import com.example.fastnotes.data.model.Note;

import java.util.List;

public class NotesRepository {
//    //    MVP
//    private NotesDatabase db;
//
//    public NotesRepository(Context context) {
//        db = new NotesDatabase(context) {
//            @Override
//            public void clearAllTables() {
//
//            }
//
//            @NonNull
//            @Override
//            protected InvalidationTracker createInvalidationTracker() {
//                return null;
//            }
//
//            @Override
//            public NoteDao noteDao() {
//                return null;
//            }
//        };
//    }
//
//    public List<Note3> getAllNotes() {
//        return db.getAllNotes();
//    }
//
//    public void addNote(Note3 note) {
//        db.addNote(note);
//    }
//
//    public void updateNote(Note3 note) {
//        db.updateNote(note);
//    }
//
//    public void deleteNote(long id) {
//        db.deleteNote(id);
//    }
    //MVVM
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NotesRepository(Application application) {
        NotesDatabase db = NotesDatabase.getDatabase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {

        return allNotes;
    }

    public void insert(Note note1) {

        new InsertNoteAsyncTask(noteDao).execute(note1);
    }

    public void update(Note note) {
        new Thread(() -> noteDao.update(note)).start();
    }

    public void delete(Note note) {
        new Thread(() -> noteDao.delete(note)).start();
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
