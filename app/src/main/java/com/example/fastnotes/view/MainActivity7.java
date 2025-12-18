package com.example.fastnotes.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.data.model.fastnotes.Note2;
import com.example.fastnotes.R;
import com.example.fastnotes.adapter.NoteAdapter4;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.ui.MainViewModel;
import com.example.fastnotes.utils.AddNoteDialog;
import com.example.fastnotes.utils.EditNoteDialog;
import java.util.ArrayList;
import java.util.List;

//    MVVM
public class MainActivity7 extends AppCompatActivity {
    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private NoteAdapter4 adapter;
    private EditText editTextTitle;
    private EditText editTextContent;
    private Button buttonAdd;
    private List<Note> notes;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter4();
        recyclerView.setAdapter(adapter);

        // Получение ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Наблюдение за изменениями данных
        viewModel.getAllNotes().observe(this, notes -> {
            adapter.submitList(notes);
        });

        // Загрузка начальных данных
        viewModel.getAllNotes();

        // Инициализация UI-компонентов
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        // Инициализация списка заметок
        notes = new ArrayList<>();

        // Настройка RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter4(this, notes, this::showEditDialog);
        recyclerView.setAdapter(adapter);

        // Обработка кнопки "Добавить"
        buttonAdd.setOnClickListener(v -> addNewNote());

        // Обработка поиска
        setupSearch();
    }

    private void addNewNote() {
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        if (!title.isEmpty() || !content.isEmpty()) {
            Note newNote = new Note(title, content);
            notes.add(newNote);
            adapter.notifyItemInserted(notes.size() - 1);

            editTextTitle.setText("");
            editTextContent.setText("");
        }
    }

    // Метод для открытия диалога редактирования
    private void showEditDialog(Note note) {
        EditNoteDialog dialog = new EditNoteDialog(this, note, updatedNote -> {
            // Обновляем заметку в списке
            int position = notes.indexOf(note);
            if (position != -1) {
                notes.set(position, updatedNote);
                adapter.notifyItemChanged(position);
            }
        });
        dialog.show();
    }

    // Настройка поиска
    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterNotes(newText);
                return true;
            }
        });
    }

    // Фильтрация заметок по запросу
    private void filterNotes(String query) {
        List<Note> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            filteredList.addAll(notes); // Если запрос пустой — показываем все заметки
        } else {
            for (Note note : notes) {
                if (note.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        note.getContent().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(note);
                }
            }
        }
        adapter.setNotes(filteredList); // Передаем отфильтрованный список в адаптер
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            showAddNoteDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Метод для показа диалога добавления заметки
    private void showAddNoteDialog() {
        AddNoteDialog dialog = new AddNoteDialog(this, new AddNoteDialog.OnNoteAddedListener() {
            @Override
            public void onNoteAdded(String title, String content) {
                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                note.setDate(System.currentTimeMillis());
                viewModel.insert(note);
                Toast.makeText(MainActivity7.this, "Note was added", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    // Метод для удаления заметки (вызывается из адаптера)
    public void deleteNote(Note note) {
        viewModel.delete(note);
        Toast.makeText(this, "Note was removed", Toast.LENGTH_SHORT).show();
    }

    public void onNoteClick(Note note) {

        showEditNoteDialog(note);
    }

    private void showEditNoteDialog(Note note) {
        com.example.fastnotes.utils.EditNoteDialog dialog = new EditNoteDialog(this, new EditNoteDialog.OnNoteAddedListener() {
            @Override
            public void onNoteAdded(String title, String content) {
                Note note = new Note();
                note.setTitle("Title");
                note.setContent("Content");
                note.setDate(System.currentTimeMillis());
                viewModel.insert(note);
                Toast.makeText(MainActivity7.this, "Note was added", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
