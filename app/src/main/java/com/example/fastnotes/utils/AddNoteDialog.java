package com.example.fastnotes.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.example.fastnotes.R;

public class AddNoteDialog {
    private Context context;
    private OnNoteAddedListener listener;

    public interface OnNoteAddedListener {
        void onNoteAdded(String title, String content);
    }

    public AddNoteDialog(Context context, OnNoteAddedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_note, null);

        EditText etTitle = dialogView.findViewById(R.id.etTitle);
        EditText etContent = dialogView.findViewById(R.id.etContent);

        builder.setView(dialogView)
                .setTitle("New note")
                .setPositiveButton("Save", (dialog, which) -> {
                    String title = etTitle.getText().toString();
                    String content = etContent.getText().toString();
                    if (!title.isEmpty() || !content.isEmpty()) {
                        listener.onNoteAdded(title, content);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
