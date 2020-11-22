package com.company1.gpasaver.util;

import com.company1.gpasaver.models.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TutorSearchMatcherTest {

    TutorSearchMatcher tutorSearchMatcher = new TutorSearchMatcher();

    @Test
    public void showAllUserShouldAlwaysMatch(){

        User testUser = new User();
        testUser.setFirstName("");

        boolean result = tutorSearchMatcher.validateUser(testUser, "Show All", "");
        assertTrue(result);
    }

    @Test
    public void invalidUserNameShouldNotMatch(){

        User testUser = new User();
        testUser.setFirstName("foo");

        boolean result = tutorSearchMatcher.validateUser(testUser, "Show All", "bar");
        assertFalse(result);
    }

    @Test
    public void containedUserNameShouldMatch(){

        User testUser = new User();
        testUser.setFirstName("foo");

        boolean result = tutorSearchMatcher.validateUser(testUser, "Show All", "foo");
        assertTrue(result);
    }

    @Test
    public void equaledClassNameShouldMatch(){

        User testUser = new User();
        testUser.setFirstName("");
        testUser.addSubjectWillTutor("Class");

        boolean result = tutorSearchMatcher.validateUser(testUser, "Class", "");
        assertTrue(result);
    }

    @Test
    public void differentClassNameShouldMatch(){

        User testUser = new User();
        testUser.setFirstName("");
        testUser.addSubjectWillTutor("Class");

        boolean result = tutorSearchMatcher.validateUser(testUser, "Another", "");
        assertFalse(result);
    }
}
