package com.company1.gpasaver;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.company1.gpasaver.database.AppDatabase;
import com.company1.gpasaver.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test the implementation of {@link com.company1.gpasaver.database.dao.UserDao}
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest {
    private static final User USER = new User(1, "Bob");
    private AppDatabase appDatabase;

    @Before public void initDB() throws Exception {
        appDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase.class)
                .allowMainThreadQueries().build();
    }

    @After public void closeDB() throws Exception {
        appDatabase.close();
    }

    @Test public void insertAndGetUser() {
        appDatabase.userDao().insertUser(USER);
        User dbUser = appDatabase.userDao().getUser();
        assertEquals(dbUser.getId(), USER.getId());
        assertEquals(dbUser.getFirstName(), USER.getFirstName());
    }

    @Test public void updateAndGetUser() {
        // Given that we have a user in the data source
        appDatabase.userDao().insertUser(USER);

        // When we are updating the name of the user
        User updatedUser = new User(USER.getId(), "new username");
        appDatabase.userDao().insertUser(updatedUser);

        //The retrieved user has the updated username
        User dbUser = appDatabase.userDao().getUser();
        assertEquals(dbUser.getId(), USER.getId());
        assertEquals(dbUser.getFirstName(), "new username");
    }

    @Test public void deleteAndGetUser() {
        // Given that we have a user in the data source
        appDatabase.userDao().insertUser(USER);

        //When we are deleting all users
        appDatabase.userDao().deleteAllUsers();

        // The user is no longer in the data source
        User dbUser = appDatabase.userDao().getUser();
        assertNull(dbUser);
    }


}
