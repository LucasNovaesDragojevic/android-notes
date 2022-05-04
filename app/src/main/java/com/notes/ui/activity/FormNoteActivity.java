package com.notes.ui.activity;

import static com.notes.enumerator.ApplicationConstants.INVALID_POSITION;
import static com.notes.enumerator.ApplicationConstants.NOTE;
import static com.notes.enumerator.ApplicationConstants.POSITION;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.notes.R;
import com.notes.model.Note;

public class FormNoteActivity extends AppCompatActivity {

    private Integer position = INVALID_POSITION;
    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_form_note);
        this.configEditTexts();
        final Intent intent = super.getIntent();
        if (intent.hasExtra(NOTE.name())) {
            final Note note = (Note) intent.getSerializableExtra(NOTE.name());
            position = intent.getIntExtra(POSITION.name(), INVALID_POSITION);
            this.fillEditTexts(note);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater().inflate(R.menu.menu_form_note_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void configEditTexts() {
        titleEditText  = super.findViewById(R.id.activity_form_note_title);
        contentEditText = super.findViewById(R.id.activity_form_note_content);
    }

    private void fillEditTexts(Note note) {
        titleEditText.setText(note.getTitle());
        contentEditText.setText(note.getContent());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (isSaveNoteMenu(item)) {
            final Note note = this.createNote();
            this.returnNote(note);
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnNote(Note note) {
        final Intent intent = new Intent();
        intent.putExtra(NOTE.name(), note);
        intent.putExtra(POSITION.name(), position);
        super.setResult(RESULT_OK, intent);
    }

    @NonNull
    private Note createNote() {
        return new Note(titleEditText.getText().toString(), contentEditText.getText().toString());
    }

    private Boolean isSaveNoteMenu(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_form_note_ic_save;
    }
}