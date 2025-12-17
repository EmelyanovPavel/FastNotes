package com.example.fastnotes.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.fastnotes.data.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase{
    public abstract NoteDao noteDao();
    private static volatile NotesDatabase INSTANCE;

    public static NotesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    NotesDatabase.class,
                                    "notes_database")
                            .fallbackToDestructiveMigration()  // на случай изменения схемы
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
