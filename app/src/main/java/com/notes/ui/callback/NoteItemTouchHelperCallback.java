package com.notes.ui.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.notes.dao.NoteDao;
import com.notes.ui.adapter.ListNoteAdapter;

public class NoteItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListNoteAdapter listNoteAdapter;
    private final NoteDao noteDao;

    public NoteItemTouchHelperCallback(ListNoteAdapter listNoteAdapter, NoteDao noteDao) {
        this.listNoteAdapter = listNoteAdapter;
        this.noteDao = noteDao;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int movementMarks = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, movementMarks);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int notePosition = viewHolder.getAdapterPosition();
        noteDao.delete(notePosition);
        listNoteAdapter.remove(notePosition);
    }
}
