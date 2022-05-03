package com.notes.ui.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.notes.R;
import com.notes.model.Note;

import java.util.List;

public class ListNoteAdapter extends BaseAdapter {

    private final Context context;
    private final List<Note> notes;

    public ListNoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View viewCreated = LayoutInflater.from(context).inflate(R.layout.item_note, viewGroup, false);
        final Note note = notes.get(position);
        final TextView title = viewCreated.findViewById(R.id.item_note_title);
        final TextView content = viewCreated.findViewById(R.id.item_note_content);
        title.setText(note.getTitle());
        content.setText(note.getContent());
        return viewCreated;
    }
}