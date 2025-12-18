package com.example.fastnotes.data.repository;

import android.content.Context;
import com.example.fastnotes.data.database.NotesDatabase;
import com.example.fastnotes.data.model.Note;
import java.util.List;

public class NotesRepository {
    //    MVP
    private NotesDatabase db;

    public NotesRepository(Context context) {
        db = new NotesDatabase(context);
    }
    public List<Note> getAllNotes() {
        return db.getAllNotes();
    }
    public void addNote(Note note) {
        db.addNote(note);
    }
    public void updateNote(Note note) {
        db.updateNote(note);
    }
    public void deleteNote(Note id) {
        db.deleteNote(id);
    }

}
