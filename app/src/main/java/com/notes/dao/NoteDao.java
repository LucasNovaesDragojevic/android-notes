package com.notes.dao;

import com.notes.model.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteDao {

    private static final List<Note> NOTES = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            NOTES.add(new Note("Title " + i, "Description " + i));
        }
    }

    public List<Note> readAll() {
        return Collections.unmodifiableList(NOTES);
    }

    public void create(Note note) {
        NOTES.add(note);
    }

    public void update(Integer position, Note note) {
        NOTES.set(position, note);
    }

    public void delete(Note note) {
        NOTES.remove(note);
    }

    public void swap(Integer initialPosition, Integer finalPosition) {
        Collections.swap(NOTES, initialPosition,finalPosition);
    }

    public void deleteAll() {
        NOTES.clear();
    }
}
