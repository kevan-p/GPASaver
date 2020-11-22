package com.company1.gpasaver;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RequestTutorUnitTest {
    requestTutor r;
    @Before
    public void init() {
        r = new requestTutor();
        r.startDate = Mockito.mock(EditText.class);
        r.fromTime = Mockito.mock(EditText.class);
        r.endTime = Mockito.mock(EditText .class);
    }


    @Test
    public void no_inputs(){
        r.fromDate = "";
        r.fromTimeStr = "";
        r.toTimeStr = "";
        assertEquals(r.validateInputs(), false);
    }
    @Test
    public void no_from_time(){
        r.fromDate = "2020-11-3";
        r.fromTimeStr = "";
        r.toTimeStr = "2020-11-3 9:55:00";
        assertEquals(r.validateInputs(), false);
    }

    @Test
    public void no_end_time(){
        r.fromDate = "2020-11-3";
        r.fromTimeStr = "2020-11-3 9:55:00";
        r.toTimeStr = "";
        assertEquals(r.validateInputs(), false);
    }

    @Test
    public void no_start_date(){
        r.fromDate = "";
        r.fromTimeStr = "2020-11-3 9:55:00";
        r.toTimeStr = "2020-11-3 9:55:00";
        assertEquals(r.validateInputs(), false);
    }

    @Test
    public void correct_inputs(){
        r.fromDate = "2020-11-3";
        r.fromTimeStr = "2020-11-3 9:55:00";
        r.toTimeStr = "2020-11-3 9:55:00";
        assertEquals(r.validateInputs(), true);
    }

    @Test
    public void cost_zero(){
        r.ft = "9:0";
        r.tt = "9:0";
        r.hourly_rate = 10;
        assertEquals(r.calculateCost(), 0.0, 0);
    }

    @Test
    public void cost_ten(){
        r.ft = "9:0";
        r.tt = "10:0";
        r.hourly_rate = 10;
        assertEquals(r.calculateCost(), 10.0, 0);
    }

    @Test
    public void cost_point(){
        r.ft = "9:0";
        r.tt = "9:25";
        r.hourly_rate = 10;
        assertEquals(r.calculateCost(), 4.166666507720947, 0);
    }

    @Test (expected = NullPointerException.class)
    public void onCreateNull() {
        r.onCreate(null);
    }

    @Test (expected = IllegalStateException.class)
    public void showtimePickerNull() {
        r.showtimePicker(null);
    }

    @Test (expected = IllegalStateException.class)
    public void showPickerNull() {
        r.showPicker(null);
    }

}