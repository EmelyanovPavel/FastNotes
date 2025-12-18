package com.example.fastnotes.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.example.fastnotes.R;
import com.example.fastnotes.data.model.Note;

public class EditNoteDialog extends Dialog {

    private EditText editTextTitle;
    private EditText editTextContent;
    private Button buttonSave;
    private Button buttonCancel;
    private OnSaveClickListener listener;
    private Note note;

    public interface OnSaveClickListener {
        void onSave(Note updatedNote);
    }

    public EditNoteDialog(@NonNull Context context, Note note, OnSaveClickListener listener) {
        super(context);
        this.note = note;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_note);

        // Инициализация View
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        // Предзаполнение полей
        editTextTitle.setText(note.getTitle());
        editTextContent.setText(note.getContent());

        // Обработка кнопок
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обновление заметки
                note.setTitle(editTextTitle.getText().toString().trim());
                note.setContent(editTextContent.getText().toString().trim());
                listener.onSave(note);
                dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}