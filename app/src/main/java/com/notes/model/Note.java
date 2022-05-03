package com.notes.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Note implements Serializable {

    private final String uuid = UUID.randomUUID().toString();
    private final String title;
    private final String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return uuid.equals(note.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
