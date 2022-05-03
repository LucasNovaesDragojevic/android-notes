package com.notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.notes.R;
import com.notes.dao.NoteDao;
import com.notes.model.Note;
import com.notes.ui.activity.adapter.ListNoteAdapter;

import java.util.List;

public class ListNoteActivity extends AppCompatActivity {

    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list_note);
        final ListView notesListView = findViewById(R.id.activity_list_note_view);
        final List<Note> notes = noteDao.readAll();
        notesListView.setAdapter(new ListNoteAdapter(this, notes));
    }
}