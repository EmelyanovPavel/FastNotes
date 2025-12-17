package com.example.fastnotes.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.data.repository.NotesRepository2;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final NotesRepository2 repository;
    private final LiveData<List<Note>> allNotes;

    public MainViewModel(Application application) {
        super(application);
        repository = new NotesRepository2(application);
        allNotes = repository.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {

        return allNotes;
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }
}
