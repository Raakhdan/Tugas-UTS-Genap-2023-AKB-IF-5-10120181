package com.AkhdanRafly_10120181_UTS.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.AkhdanRafly_10120181_UTS.view.activity.AddNoteActivity;
import com.AkhdanRafly_10120181_UTS.view.activity.MainActivity;
import com.AkhdanRafly_10120181_UTS.R;
import com.AkhdanRafly_10120181_UTS.adapter.NoteAdapter;
import com.AkhdanRafly_10120181_UTS.model.Note;
import com.AkhdanRafly_10120181_UTS.database.Helper;
import com.AkhdanRafly_10120181_UTS.NoteInterface;

import java.util.ArrayList;
import java.util.List;

public class NoteFragment extends Fragment  {

    private MainActivity mainActivity;
    private List<Note> note;
    private NoteInterface noteInterface;
    private RecyclerView recyclerView;
    private com.AkhdanRafly_10120181_UTS.adapter.NoteAdapter noteAdapter;
    private FloatingActionButton addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_menu_catatan, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().show();

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.note);
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddNoteActivity.class));
        });

        read();
    }

    @Override
    public void onResume() {
        super.onResume();
        read();
    }

    private void read() {
        note = new ArrayList<Note>();
        noteInterface = new Helper(getContext());
        Cursor cursor = noteInterface.read();
        if (cursor.moveToFirst()){
            do {
                Note n = new Note(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)

                );

                note.add(n);
            } while (cursor.moveToNext());
        }

        noteAdapter = new NoteAdapter(note);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }
}

//10120181 - Akhdan Rafly - IF5