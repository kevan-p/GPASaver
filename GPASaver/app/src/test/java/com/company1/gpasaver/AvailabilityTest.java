package com.company1.gpasaver;

import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvailabilityTest {

    Availability ava;

    @Test
    public void checkIsCourseName(){
        ava = new Availability(new Course("id", "cName"), "MF9AM-10AM");
        assertEquals("cName", ava.getCourse().getName());
    }

    @Test
    public void checkIsCourseId(){
        ava = new Availability(new Course("id", "cName"), "MF9AM-10AM");
        assertEquals("id", ava.getCourse().getId());
    }

    @Test
    public void checkIsCourseTime(){
        ava = new Availability(new Course("id", "cName"), "MF9AM-10AM");
        assertEquals("MF9AM-10AM", ava.getTime());
    }

}
