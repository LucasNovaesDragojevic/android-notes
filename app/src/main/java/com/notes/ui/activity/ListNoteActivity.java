package com.notes.ui.activity;

import static com.notes.enumerator.ApplicationConstants.CREATE_NOTE;
import static com.notes.enumerator.ApplicationConstants.NOTE;
import static com.notes.enumerator.ApplicationConstants.NOTE_CREATED;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
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
    private ListNoteAdapter listNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_list_note);
        final List<Note> notes = new ArrayList<>(noteDao.readAll());
        this.configRecyclerView(notes);
        this.configButtonNewNote();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;

        if (isResultWithNewNote(requestCode, resultCode, data))
            this.createAndAddNote(data);
    }

    private void createAndAddNote(@NonNull Intent data) {
        final Note note = (Note) data.getSerializableExtra(NOTE.name());
        noteDao.create(note);
        listNoteAdapter.addNote(note);
    }

    private boolean isResultWithNewNote(int requestCode, int resultCode, @NonNull Intent data) {
        return isRequestToCreateNote(requestCode) && isResultNoteCreated(resultCode) && hasNote(data);
    }

    private boolean hasNote(@NonNull Intent data) {
        return data.hasExtra(NOTE.name());
    }

    private boolean isResultNoteCreated(int resultCode) {
        return resultCode == NOTE_CREATED.ordinal();
    }

    private boolean isRequestToCreateNote(int requestCode) {
        return requestCode == CREATE_NOTE.ordinal();
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
            super.startActivityForResult(intent, CREATE_NOTE.ordinal());
        });
    }
}