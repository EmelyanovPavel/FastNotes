package com.example.fastnotes;

import android.app.Application;
import androidx.room.Room;
import com.example.fastnotes.data.database.NotesDatabase;

public class App extends Application {
    private static NotesDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(
                this, NotesDatabase.class, "notes_db")
                .build();
    }

    public static NotesDatabase getDatabase() {
        return database;
    }
}
