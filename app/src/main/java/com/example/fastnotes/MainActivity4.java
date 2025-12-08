package com.example.fastnotes;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter3 adapter;
    private List<Note2> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Инициализация UI-компонентов
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.notesRecyclerView);

        // Настройка RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesList = new ArrayList<>();
        adapter = new NoteAdapter3(notesList);
        recyclerView.setAdapter(adapter);

        // Обработчик нажатия FAB
        fab.setOnClickListener(view -> {
            // Здесь можно добавить логику создания новой заметки
            addSampleNote();
        });

        // Добавляем тестовые заметки
        addSampleNotes();
    }

    // Метод для добавления тестовой заметки
    private void addSampleNote() {
        Note2 newNote = new Note2("New note", "Enter the text of the note here...");
        notesList.add(newNote);
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(notesList.size() - 1);
    }

    // Метод для заполнения списка тестовыми заметками
    private void addSampleNotes() {
        notesList.add(new Note2("Meeting with a client", "Discussing project details and delivery deadlines."));
        notesList.add(new Note2("Buy products", "Milk, bread, eggs, fruits."));
        notesList.add(new Note2("Preparing for the presentation", "Check the slides and practice your speech."));
        adapter.notifyDataSetChanged();
    }
}
