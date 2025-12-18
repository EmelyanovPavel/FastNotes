package com.example.data.model.fastnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fastnotes.R;
import com.example.fastnotes.data.model.Note3;
import java.util.List;

public class NoteAdapter5 extends RecyclerView.Adapter<NoteAdapter5.ViewHolder> {

    private List<Note3> notes;
    private Context context;

    public NoteAdapter5(Context context, List<Note3> notes) {
        this.context = context;
        this.notes = notes;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note4, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note3 note = notes.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewContent.setText(note.getContent());

        // Запуск анимации для нового элемента
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        holder.itemContainer.startAnimation(animation);
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewContent;
        LinearLayout itemContainer;

        ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.etTitle);
            textViewContent = itemView.findViewById(R.id.etContent);
            itemContainer = itemView.findViewById(R.id.note_item_container);
        }
    }
}