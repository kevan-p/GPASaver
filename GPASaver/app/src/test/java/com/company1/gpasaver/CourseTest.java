package com.company1.gpasaver;

import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseTest {

    Course course;

    @Test
    public void checkCourseName(){
        course = new Course("1", "cName1");
        assertEquals("cName1", course.getName());
    }

    @Test
    public void checkCourseId(){
        course = new Course("1", "cName1");
        assertEquals("1", course.getId());
    }


}
