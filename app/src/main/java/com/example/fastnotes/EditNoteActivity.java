package com.example.fastnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private Button btnSave;
    private long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);

        // Получаем переданные данные
        Intent intent = getIntent();
        noteId = getIntent().getLongExtra("NOTE_ID", -1);
        String title = getIntent().getStringExtra("NOTE_TITLE");
        String content = getIntent().getStringExtra("NOTE_CONTENT");
        if (noteId != -1) {
            etTitle.setText(title);
            etContent.setText(content);
        }
        // Заполняем поля, если данные есть
        if (title != null) etTitle.setText(title);
        if (content != null) etContent.setText(content);

//        btnSave.setOnClickListener(v -> saveNote(noteId));
        btnSave.setOnClickListener(v -> {
            String titleText = etTitle.getText().toString();
            String contentText = etContent.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("note_id", noteId == -1 ? System.currentTimeMillis() : noteId);
            resultIntent.putExtra("note_title", titleText);
            resultIntent.putExtra("note_content", contentText);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

//    private void saveNote(long noteId) {
//        String title = etTitle.getText().toString();
//        String content = etContent.getText().toString();
//
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra("NOTE_ID", noteId == -1 ? System.currentTimeMillis() : noteId);
//        resultIntent.putExtra("NOTE_TITLE", title);
//        resultIntent.putExtra("NOTE_CONTENT", content);
//
//        setResult(RESULT_OK, resultIntent);
//        finish();
//    }
}
