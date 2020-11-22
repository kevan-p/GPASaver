package com.company1.gpasaver;

import com.company1.gpasaver.ui.profile.EditProfile;

import org.junit.Test;

import static org.junit.Assert.*;

public class EditProfileTest {

    EditProfile ep = new EditProfile();

    @Test
    public void onCreate() {
    }

    @Test
    public void correct_id(){
        String courseId = "1";
        assertEquals(true, ep.isValidId(courseId));
    }

    @Test
    public void correct_time(){
        String time = "MWF 10AM-9PM";
        assertEquals(true,ep.isValidTime(time));
    }

    @Test
    public void no_input_id(){
        String courseId = "";
        assertEquals(false, ep.isValidId(courseId));
    }

    @Test
    public void no_input_time(){
        String time = "";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void id_out_range_1(){
        String courseId = "0";
        assertEquals(false, ep.isValidId(courseId));
    }

    @Test
    public void id_out_range_2(){
        String courseId = "15";
        assertEquals(false, ep.isValidId(courseId));
    }

    @Test
    public void invalid_time_1(){
        String time = "AB";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_2(){
        String time = "MF-";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_3(){
        String time = "MF ";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_4(){
        String time = "MF W R";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_5(){
        String time = "MMFTRWW";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_6(){
        String time = "MWF 10QM-WM";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_7(){
        String time = "MWF 10AM-";
        assertEquals(false,ep.isValidTime(time));
    }

    @Test
    public void invalid_time_8(){
        String time = "MWF 10AM-M115";
        assertEquals(false,ep.isValidTime(time));
    }
}