package com.AkhdanRafly_10120181_UTS;

import android.database.Cursor;

import com.AkhdanRafly_10120181_UTS.model.Note;

public interface NoteInterface {

    public Cursor read();
    public boolean create(Note note);
    public boolean update(Note note);
    public boolean delete(String id);
}

//10120181 - Akhdan Rafly - IF5