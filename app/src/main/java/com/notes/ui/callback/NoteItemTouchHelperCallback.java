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
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        final int initialPosition = viewHolder.getAdapterPosition();
        final int finalPosition = target.getAdapterPosition();
        this.swapNotes(initialPosition, finalPosition);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int notePosition = viewHolder.getAdapterPosition();
        this.removeNote(notePosition);
    }

    private void removeNote(int notePosition) {
        noteDao.delete(notePosition);
        listNoteAdapter.remove(notePosition);
    }

    private void swapNotes(int initialPosition, int finalPosition) {
        noteDao.swap(initialPosition, finalPosition);
        listNoteAdapter.swap(initialPosition, finalPosition);
    }
}
