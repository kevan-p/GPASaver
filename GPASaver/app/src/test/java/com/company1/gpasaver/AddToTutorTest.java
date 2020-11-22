package com.company1.gpasaver;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddToTutorTest {

    AddMyselfTutorActivity activity = new AddMyselfTutorActivity();

    @Test
    public void onCreate() {
    }

    @Test
    public void gpa_test1(){
        String gpa = "3.0";
        assertEquals(true, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test2(){
        String gpa = "0.0";
        assertEquals(true, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test3(){
        String gpa = "111.0";
        assertEquals(false, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test4(){
        String gpa = "1.1.1.0";
        assertEquals(false, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test5(){
        String gpa = "-1.0";
        assertEquals(false, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test6(){
        String gpa = "3.892";
        assertEquals(true, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test7(){
        String gpa = "4.5";
        assertEquals(false, activity.isValidGpa(gpa));
    }

    @Test
    public void gpa_test8(){
        String gpa = "4.0";
        assertEquals(true, activity.isValidGpa(gpa));
    }

    @Test
    public void correct_time1(){
        String time = "MWF 10AM-9PM";
        assertEquals(true,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_1(){
        String time = "AB";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_2(){
        String time = "MF-";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_3(){
        String time = "MF ";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_4(){
        String time = "MF W R";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_5(){
        String time = "MMFTRWW";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_6(){
        String time = "MWF 10QM-WM";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_7(){
        String time = "MWF 10AM-";
        assertEquals(false,activity.isValidTime(time));
    }

    @Test
    public void invalid_time_8(){
        String time = "MWF 10AM-M115";
        assertEquals(false,activity.isValidTime(time));
    }
}
