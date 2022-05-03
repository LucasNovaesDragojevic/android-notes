package com.notes.ui.activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_form_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater().inflate(R.menu.menu_form_note_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_form_note_ic_save) {
            final EditText titleEditText = super.findViewById(R.id.activity_form_note_title);
            final EditText contentEditText = super.findViewById(R.id.activity_form_note_content);
            final Note note = new Note(titleEditText.getText().toString(), contentEditText.getText().toString());
            final Intent intent = new Intent();
            intent.putExtra("note", note);
            super.setResult(2, intent);
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}