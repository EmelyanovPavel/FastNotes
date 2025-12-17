package com.example.fastnotes.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.fastnotes.NotesView;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.data.repository.NotesRepository;
import java.util.List;

public class NotesPresenter {
    //MVP
    private NotesRepository repository;
    private NotesView view;

    public NotesPresenter(NotesView view, Context context) {
        this.view = view;
        this.repository = new NotesRepository(context);
    }

    public void loadNotes() {
        List<Note> notes = repository.getAllNotes();
        view.showNotes(notes);
    }

    public void addNote(String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setDate(System.currentTimeMillis());
        repository.addNote(note);
        loadNotes(); // Обновляем список
    }

    public void deleteNote(Note id) {
        repository.deleteNote(id);
        loadNotes();
    }
}
