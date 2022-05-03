package com.notes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.notes.R;
import com.notes.dao.NoteDao;
import com.notes.model.Note;
import com.notes.ui.adapter.ListNoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListNoteActivity extends AppCompatActivity {

    private final NoteDao noteDao = new NoteDao();
    private final List<Note> notes = new ArrayList<>();
    private ListNoteAdapter listNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list_note);
        notes.addAll(noteDao.readAll());
        this.configRecyclerView(notes);
        this.configButtonNewNote();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2 && data != null && data.hasExtra("note")) {
            final Note note = (Note) data.getSerializableExtra("note");
            noteDao.create(note);
            listNoteAdapter.addNote(note);
        }
    }

    private void configRecyclerView(List<Note> notes) {
        final RecyclerView notesRecyclerView = super.findViewById(R.id.activity_list_note_recycler_view);
        this.configAdapter(notes, notesRecyclerView);
    }

    private void configAdapter(List<Note> notes, RecyclerView notesRecyclerView) {
        listNoteAdapter = new ListNoteAdapter(this, notes);
        notesRecyclerView.setAdapter(listNoteAdapter);
    }

    private void configButtonNewNote() {
        final View btnNewNote = super.findViewById(R.id.activity_list_note_new_note);
        btnNewNote.setOnClickListener((view) -> {
            final Intent intent = new Intent(this, FormNoteActivity.class);
            super.startActivityForResult(intent, 1);
        });
    }
}