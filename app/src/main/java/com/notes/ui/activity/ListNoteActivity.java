package com.notes.ui.activity;

import static com.notes.enumerator.ApplicationConstants.CREATE_NOTE;
import static com.notes.enumerator.ApplicationConstants.EDIT_NOTE;
import static com.notes.enumerator.ApplicationConstants.INVALID_POSITION;
import static com.notes.enumerator.ApplicationConstants.NOTE;
import static com.notes.enumerator.ApplicationConstants.POSITION;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.notes.R;
import com.notes.dao.NoteDao;
import com.notes.model.Note;
import com.notes.ui.adapter.ListNoteAdapter;
import com.notes.ui.callback.NoteItemTouchHelperCallback;

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

        if (isResultWithNewNote(requestCode, data))
            if (isResultOk(resultCode))
                this.createAndAddNote(data);

        if (isResultToUpdateNote(requestCode, data)) {
            if (isResultOk(resultCode)) {
                final Note note = (Note) data.getSerializableExtra(NOTE.name());
                final Integer position = data.getIntExtra(POSITION.name(), INVALID_POSITION);
                if (isValidPosition(position))
                    this.updateDaoAndAdapter(note, position);
                else
                    this.notifyUserAboutProblem();
            }
        }
    }

    private void notifyUserAboutProblem() {
        Toast.makeText(this, "Problem encountered when update note.", Toast.LENGTH_SHORT).show();
    }

    private void updateDaoAndAdapter(Note note, Integer position) {
        noteDao.update(position, note);
        listNoteAdapter.update(position, note);
    }

    private boolean isValidPosition(Integer position) {
        return !position.equals(INVALID_POSITION);
    }

    private boolean isResultToUpdateNote(int requestCode, @NonNull Intent data) {
        return isRequestToEditNote(requestCode) && hasNote(data);
    }

    private boolean isRequestToEditNote(int requestCode) {
        return requestCode == EDIT_NOTE.ordinal();
    }

    private void createAndAddNote(@NonNull Intent data) {
        final Note note = (Note) data.getSerializableExtra(NOTE.name());
        noteDao.create(note);
        listNoteAdapter.addNote(note);
    }

    private boolean isResultWithNewNote(int requestCode, @NonNull Intent data) {
        return isRequestToCreateNote(requestCode) && hasNote(data);
    }

    private boolean hasNote(@NonNull Intent data) {
        return data.hasExtra(NOTE.name());
    }

    private boolean isResultOk(int resultCode) {
        return resultCode == RESULT_OK;
    }

    private boolean isRequestToCreateNote(int requestCode) {
        return requestCode == CREATE_NOTE.ordinal();
    }

    private void configRecyclerView(List<Note> notes) {
        final RecyclerView notesRecyclerView = super.findViewById(R.id.activity_list_note_recycler_view);
        this.configAdapter(notes, notesRecyclerView);
        this.configItemTouchHelper(notesRecyclerView);
    }

    private void configItemTouchHelper(RecyclerView notesRecyclerView) {
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchHelperCallback(listNoteAdapter, noteDao));
        itemTouchHelper.attachToRecyclerView(notesRecyclerView);
    }

    private void configAdapter(List<Note> notes, RecyclerView notesRecyclerView) {
        listNoteAdapter = new ListNoteAdapter(this, notes);
        notesRecyclerView.setAdapter(listNoteAdapter);
        listNoteAdapter.setOnItemClickListener(this::goToFormNoteActivityToEdit);
    }

    private void configButtonNewNote() {
        final View btnNewNote = super.findViewById(R.id.activity_list_note_new_note);
        btnNewNote.setOnClickListener(view -> this.goToFormNoteActivityToCreate());
    }

    private void goToFormNoteActivityToCreate() {
        final Intent intent = new Intent(this, FormNoteActivity.class);
        super.startActivityForResult(intent, CREATE_NOTE.ordinal());
    }

    private void goToFormNoteActivityToEdit(Note note, Integer position) {
        final Intent intent = new Intent(this, FormNoteActivity.class);
        intent.putExtra(NOTE.name(), note);
        intent.putExtra(POSITION.name(), position);
        startActivityForResult(intent, EDIT_NOTE.ordinal());
    }
}