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

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.NoteViewHolder> {

    private final Context context;
    private final List<Note> notes;

    public ListNoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ListNoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNoteAdapter.NoteViewHolder holder, int position) {
        final Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void addNote(Note note) {
        notes.add(note);
        notifyItemInserted(notes.size() + 1);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView content;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.item_note_title);
            this.content = itemView.findViewById(R.id.item_note_content);
        }

        public void bind(Note note) {
            this.title.setText(note.getTitle());
            this.content.setText(note.getContent());
        }
    }
}