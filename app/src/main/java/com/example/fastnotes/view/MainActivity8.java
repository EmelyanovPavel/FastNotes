package com.example.fastnotes.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.model.fastnotes.NoteAdapter5;
import com.example.fastnotes.NotesView;
import com.example.fastnotes.R;
import com.example.fastnotes.adapter.NoteAdapter4;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.data.model.Note3;
import com.example.fastnotes.presenter.NotesPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity8 extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextContent;
    private Button buttonAdd;
    private RecyclerView recyclerView;
    private NoteAdapter5 adapter;
    private List<Note3> notes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Инициализация UI-компонентов
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonAdd = findViewById(R.id.fabAdd);
        recyclerView = findViewById(R.id.recyclerView);

        // Инициализация списка заметок
        notes = new ArrayList<>();

        // Настройка RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter5(this, notes);
        recyclerView.setAdapter(adapter);

        // Обработка нажатия кнопки "Добавить заметку"
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNote();
            }
        });
    }

    private void addNewNote() {
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        // Проверка на пустоту
        if (!title.isEmpty() || !content.isEmpty()) {
            // Создание новой заметки
            Note3 newNote = new Note3(title, content);

            // Добавление в список
            notes.add(newNote);

            // Уведомление адаптера о новом элементе (запустит анимацию)
            adapter.notifyItemInserted(notes.size() - 1);

            // Очистка полей ввода
            editTextTitle.setText("");
            editTextContent.setText("");
        }
    }
}
