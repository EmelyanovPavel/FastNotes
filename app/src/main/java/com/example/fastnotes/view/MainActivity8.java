package com.example.fastnotes.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastnotes.NotesView;
import com.example.fastnotes.R;
import com.example.fastnotes.adapter.NoteAdapter4;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.presenter.NotesPresenter;

import java.util.List;

////MVP
public class MainActivity8 extends AppCompatActivity implements NotesView {

    private NotesPresenter presenter;
    private RecyclerView recyclerView;
    private NoteAdapter4 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter4();
        recyclerView.setAdapter(adapter);

        presenter = new NotesPresenter(this, this);
        presenter.loadNotes();
    }

    @Override
    public void showNotes(List<Note> notes1) {
        adapter.submitList(notes1);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Методы для UI-событий (например, кнопка "Добавить")
    public void onAddNoteClick(View view) {
        // Открываем диалог для ввода заметки
        showAddNoteDialog();
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // ... настройка диалога
        builder.setPositiveButton("Save", (dialog, which) -> {
            String title = ""/* из поля ввода */;
            String content = ""/* из поля ввода */;
            presenter.addNote(title, content);
        });
        builder.show();
    }
}
