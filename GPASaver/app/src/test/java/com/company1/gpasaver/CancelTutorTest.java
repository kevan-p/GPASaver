package com.company1.gpasaver;

import com.company1.gpasaver.models.CancelTutor;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CancelTutorTest {
    CancelTutor ctRequest;
    User tutor;
    Course course;

    @Test
    public void checkCancelTutorRequest1(){
        tutor = new User(1, "bob");
        course = new Course("id", "class name");
        ctRequest = new CancelTutor(tutor, course);
        assertEquals(true, ctRequest.getTutor() != null);
    }

    @Test
    public void checkCancelTutorRequest2(){
        tutor = new User(2, "john");
        course = new Course("id2", "class name 2");
        ctRequest = new CancelTutor(tutor, course);
        assertEquals(true, ctRequest.getCourse() != null);
    }
}
