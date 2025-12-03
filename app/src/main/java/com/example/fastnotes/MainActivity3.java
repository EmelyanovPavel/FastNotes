package com.example.fastnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity3 extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton fab;
    private ArrayList<String> notes = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView = findViewById(R.id.listView);
        fab = findViewById(R.id.fab);

        // Инициализация адаптера
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(adapter);

        // Обработка нажатия FAB
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, EditNoteActivity.class);
            startActivity(intent);
        });

        SharedPreferences prefs = getSharedPreferences("notes", MODE_PRIVATE);
        Set<String> savedNotes = prefs.getStringSet("notes_list", new HashSet<>());
        notes.addAll(savedNotes);
        adapter.notifyDataSetChanged();
    }
}
