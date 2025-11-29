package com.example.fastnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listNotes;
    private Button btnAdd;
    private List<Note> notes;
    private NoteAdapter adapter;
    private static final String PREF_NAME = "notes_pref";
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        listNotes = findViewById(R.id.list_notes);
        btnAdd = findViewById(R.id.btn_add);

        notes = new ArrayList<>();
        adapter = new NoteAdapter(this, notes);
        listNotes.setAdapter((ListAdapter) adapter);

        // Загрузка заметок (в примере — заглушка)
        loadNotes();

        // Переход к созданию новой заметки
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditNoteActivity.class);
            startActivityForResult(intent, 1);
        });

        // Контекстное меню для списка
        registerForContextMenu(listNotes);
    }

    private void loadNotes() {
        // Здесь должна быть загрузка из БД/SharedPreferences
        notes.add(new Note(1, "First note", "First note text"));
        adapter.notifyDataSetChanged();

        notes.clear();
        Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("note_")) {
                String[] parts = key.split("_", 3);
                if (parts.length == 3) {
                    long id = Long.parseLong(parts[1]);
                    String title = prefs.getString("note_" + id + "_title", "");
                    String content = prefs.getString("note_" + id + "_content", "");
                    notes.add(new Note(id, title, content));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void saveNote(Note note) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("note_" + note.getId() + "_title", note.getTitle());
        editor.putString("note_" + note.getId() + "_content", note.getContent());
        editor.apply();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Note note = notes.get(position);

        if (item.getItemId() == R.id.menu_edit) {
            Intent intent = new Intent(this, EditNoteActivity.class);
            intent.putExtra("note_id", note.getId());
            intent.putExtra("note_title", note.getTitle());
            intent.putExtra("note_content", note.getContent());
            startActivityForResult(intent, 1);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            notes.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            long id = data.getLongExtra("note_id", -1);
            String title = data.getStringExtra("note_title");
            String content = data.getStringExtra("note_content");

            Note note = new Note(id, title, content);
            saveNote(note);

            // Если заметка новая — добавляем в список
            if (id == -1) {
                note.setId(System.currentTimeMillis()); // используем timestamp как ID
                notes.add(note);
            } else {
                // Если заметка существующая — обновляем
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getId() == id) {
                        notes.set(i, note);
                        break;
                    }
                }
            }
            // Обновление списка после сохранения заметки
            loadNotes(); // или более умная логика обновления
            adapter.notifyDataSetChanged();
        }
    }
}