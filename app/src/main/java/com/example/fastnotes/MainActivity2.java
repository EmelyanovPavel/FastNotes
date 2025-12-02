package com.example.fastnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter2 adapter;
    private List<Note> notes = new ArrayList<>();
    private FloatingActionButton fabAdd;

    private static final int REQUEST_CODE_EDIT = 1;
    private static final int REQUEST_CODE_ADD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerView);
        fabAdd = findViewById(R.id.fabAdd);

        setupRecyclerView();
        setupFab();
    }

    private void setupRecyclerView() {
        adapter = new NoteAdapter2(
                notes,
                note -> {
                    // Переход к редактированию заметки
                    Intent intent = new Intent(this, EditNoteActivity.class);
                    intent.putExtra("NOTE_ID", note.getId());
                    intent.putExtra("NOTE_TITLE", note.getTitle());
                    intent.putExtra("NOTE_CONTENT", note.getContent());
                    startActivityForResult(intent, REQUEST_CODE_EDIT);
                },
                note -> {
                    // Показ контекстного меню при долгом нажатии
                    showContextMenu(note);
                    return true;
                }
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        fabAdd.setOnClickListener(v -> {
            // Переход к созданию новой заметки
            Intent intent = new Intent(this, EditNoteActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        });
    }

    // Показ контекстного меню
    private void showContextMenu(Note note) {
        PopupMenu popup = new PopupMenu(this, recyclerView);
        popup.inflate(R.menu.context_menu);

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit) {
                Intent editIntent = new Intent(this, EditNoteActivity.class);
                editIntent.putExtra("NOTE_ID", note.getId());
                editIntent.putExtra("NOTE_TITLE", note.getTitle());
                editIntent.putExtra("NOTE_CONTENT", note.getContent());
                startActivityForResult(editIntent, REQUEST_CODE_EDIT);
                return true;
            } else if (item.getItemId() == R.id.action_delete) {
                notes.remove(note);
                adapter.notifyDataSetChanged();
                return true;
            }
            return false;
        });

        popup.show();
    }

    // Обработка результата от EditNoteActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            long id = data.getLongExtra("NOTE_ID", -1);
            String title = data.getStringExtra("NOTE_TITLE");
            String content = data.getStringExtra("NOTE_CONTENT");

            Note note = new Note(id, title, content);

            if (requestCode == REQUEST_CODE_ADD) {
                // Добавляем новую заметку
                notes.add(note);
            } else if (requestCode == REQUEST_CODE_EDIT) {
                // Обновляем существующую заметку
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getId() == id) {
                        notes.set(i, note);
                        break;
                    }
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}