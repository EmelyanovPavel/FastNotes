package com.example.fastnotes;

import com.example.fastnotes.data.model.Note;

import java.util.List;

public interface NotesView {
    void showNotes(List<Note> notes);
    void showError(String message);
}
