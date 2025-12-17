package com.example.fastnotes.view;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fastnotes.EditNoteDialog;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.utils.AddNoteDialog;
import com.example.fastnotes.ui.MainViewModel;
import com.example.fastnotes.adapter.NoteAdapter4;
import com.example.fastnotes.R;

//    MVVM
public class MainActivity6 extends AppCompatActivity {
    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private NoteAdapter4 adapter;

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
                Toast.makeText(MainActivity6.this, "Note was added", Toast.LENGTH_SHORT).show();
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
        EditNoteDialog dialog = new EditNoteDialog(this, new EditNoteDialog.OnNoteAddedListener() {
            @Override
            public void onNoteAdded(String title, String content) {
                Note note = new Note();
                note.setTitle("Title");
                note.setContent("Content");
                note.setDate(System.currentTimeMillis());
                viewModel.insert(note);
                Toast.makeText(MainActivity6.this, "Note was added", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show2();
    }
}
