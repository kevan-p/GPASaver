package com.company1.gpasaver.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.company1.gpasaver.database.dao.UserDao;
import com.company1.gpasaver.models.User;

/**
 * Room Datastore: https://medium.com/androiddevelopers/7-steps-to-room-27a5fe5f99b2
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            // This needs to be updated when you change a Database model.
        }
    };

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "GPASaver.db")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }

    @NonNull @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override public void clearAllTables() {

    }

    // Put all DAOs here for access through the AppDatabase instance.
    // Look at the test class to understand how to access DAOs.
    public abstract UserDao userDao();
}
