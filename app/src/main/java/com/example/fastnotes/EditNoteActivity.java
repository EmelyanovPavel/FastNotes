package com.example.fastnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class EditNoteActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);

        // Получаем переданные данные
        long id = getIntent().getLongExtra("NOTE_ID", -1);
        String title = getIntent().getStringExtra("NOTE_TITLE");
        String content = getIntent().getStringExtra("NOTE_CONTENT");

        // Заполняем поля, если данные есть
        if (title != null) etTitle.setText(title);
        if (content != null) etContent.setText(content);

        btnSave.setOnClickListener(v -> saveNote(id));
    }

    private void saveNote(long id) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("NOTE_ID", id == -1 ? System.currentTimeMillis() : id);
        resultIntent.putExtra("NOTE_TITLE", title);
        resultIntent.putExtra("NOTE_CONTENT", content);

        setResult(RESULT_OK, resultIntent);
        finish();

        //Homework #15
        SharedPreferences prefs = getSharedPreferences("notes", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String noteText = etContent.getText().toString();
        Set<String> savedNotes = prefs.getStringSet("notes_list", new HashSet<>());
        savedNotes.add(noteText);
        editor.putStringSet("notes_list", savedNotes);
        editor.apply();
    }
}