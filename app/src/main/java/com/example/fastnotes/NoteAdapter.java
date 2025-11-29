package com.example.fastnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

//public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
public class NoteAdapter extends ArrayAdapter<Note> {
    private List<Note> notes;
    //    Homework 14
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(Note note);
    }

    //    public NoteAdapter(MainActivity mainActivity, List<Note> notes) {
//        this.notes = notes;
//        //Homework 14
//        this.listener = listener;
//        this.longClickListener = longClickListener;
//    }
    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
        this.context = context;
        this.notes = notes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    android.R.layout.simple_list_item_1, parent, false);
        }

        Note note = notes.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(note.getTitle());
        return convertView;
    }

//    @NonNull
//    @Override
//    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_note, parent, false);
//        return new NoteViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
//        Note note = notes.get(position);
//        holder.tvTitle.setText(note.getTitle());
//        holder.tvContent.setText(note.getContent());
//    }
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//    static class NoteViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTitle, tvContent;
//        public NoteViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvTitle = itemView.findViewById(R.id.tvTitle);
//            tvContent = itemView.findViewById(R.id.tvContent);
//        }
//    }
}