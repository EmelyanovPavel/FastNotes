package com.example.fastnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Создаём список заметок
        noteList = new ArrayList<>();
        noteList.add(new Note("Note #1", "This's a first note."));
        noteList.add(new Note("Note #2", "One more note."));


        // Настраиваем адаптер
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);

        // Добавляем отступы
        int spacing = 16; // dp
        recyclerView.addItemDecoration(new SpacingItemDecorator(spacing));
    }
}