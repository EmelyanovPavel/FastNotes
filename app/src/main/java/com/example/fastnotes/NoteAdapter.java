package com.example.fastnotes;

//import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
//public class NoteAdapter extends ArrayAdapter<Note> {
    //Homework #13
    private List<Note> notes;

    //    Homework 14
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
//    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(Note note);
    }
//    homework #14
    public NoteAdapter(MainActivity2 mainActivity2, List<Note> notes) {
        this.notes = notes;
        //Homework 14
        this.listener = listener;
        this.longClickListener = longClickListener;
    }
//    homework #13
    public NoteAdapter(List<Note> notes) {
//        super(context, 0, notes);
        this.notes = notes;
    }

//    Homework #13
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}