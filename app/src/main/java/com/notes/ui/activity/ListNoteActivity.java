package com.notes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.notes.R;
import com.notes.dao.NoteDao;
import com.notes.model.Note;
import com.notes.ui.adapter.ListNoteAdapter;

import java.util.List;

public class ListNoteActivity extends AppCompatActivity {

    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list_note);
        final List<Note> notes = noteDao.readAll();
        this.configRecyclerView(notes);
        final View btnNewNote = super.findViewById(R.id.activity_list_note_new_note);
        btnNewNote.setOnClickListener((view) -> {
            final Intent intent = new Intent(this, FormNoteActivity.class);
            super.startActivity(intent);
        });
    }

    private void configRecyclerView(List<Note> notes) {
        final RecyclerView notesRecyclerView = findViewById(R.id.activity_list_note_recycler_view);
        this.configAdapter(notes, notesRecyclerView);
    }

    private void configAdapter(List<Note> notes, RecyclerView notesRecyclerView) {
        final ListNoteAdapter listNoteAdapter = new ListNoteAdapter(this, notes);
        notesRecyclerView.setAdapter(listNoteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<Note> notes = noteDao.readAll();
        configRecyclerView(notes);
    }
}