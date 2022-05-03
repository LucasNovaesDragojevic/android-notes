package com.notes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.notes.R;
import com.notes.model.Note;

import java.util.List;

public class ListNoteAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<Note> notes;

    public ListNoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Note note = notes.get(position);
        final TextView title = holder.itemView.findViewById(R.id.item_note_title);
        final TextView content = holder.itemView.findViewById(R.id.item_note_content);
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private static class NoteViewHolder extends RecyclerView.ViewHolder {

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}