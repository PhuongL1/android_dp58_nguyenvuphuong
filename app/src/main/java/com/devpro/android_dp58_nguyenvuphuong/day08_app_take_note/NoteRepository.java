package com.devpro.android_dp58_nguyenvuphuong.day08_app_take_note;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getDatabase(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public LiveData<Note> getNoteById(int noteId) {
        return noteDao.getNoteById(noteId);
    }

    public void insert(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insert(note);
        });
    }

    public void update(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.update(note);
        });
    }

    public void delete(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.delete(note);
        });
    }
}