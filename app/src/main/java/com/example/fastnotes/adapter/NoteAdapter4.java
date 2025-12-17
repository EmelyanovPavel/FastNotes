package com.example.fastnotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fastnotes.R;
import com.example.fastnotes.data.model.Note;
import com.example.fastnotes.view.MainActivity6;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter4 extends RecyclerView.Adapter<NoteAdapter4.NoteViewHolder>{
    private List<Note> notes = new ArrayList<>();
    private Context context;
    private OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
        void onDeleteClick(Note note);
    }

    public NoteAdapter4(MainActivity6 mainActivity) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note3, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.tvTitle.setText(current.getTitle());
        holder.tvContent.setText(current.getContent());

//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//        holder.tvDate.setText(sdf.format(current.getDate()));


        holder.itemView.setOnClickListener(v -> listener.onNoteClick(current));
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(current));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void submitList(List<Note> newNotes) {
        notes = newNotes;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvDate;
        View itemView;
        View deleteButton;

        NoteViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
            deleteButton = itemView; // Для простоты: клик по элементу = удаление
        }
    }
}
