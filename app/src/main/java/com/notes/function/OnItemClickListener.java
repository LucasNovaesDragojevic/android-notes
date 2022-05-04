package com.notes.function;

import androidx.annotation.NonNull;

import com.notes.model.Note;

@FunctionalInterface
public interface OnItemClickListener {

    void run(@NonNull Note note, @NonNull Integer position);
}
