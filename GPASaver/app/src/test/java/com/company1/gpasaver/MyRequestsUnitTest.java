package com.company1.gpasaver;


import com.company1.gpasaver.models.RealTutorRequestEntity;

import com.company1.gpasaver.ui.requests.MyRequestsActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MyRequestsUnitTest {
    MyRequestsActivity r;
    RealTutorRequestEntity e;
    JSONObject entity;
    @Before
    public void init() throws JSONException {
        r = new MyRequestsActivity();
        e = new RealTutorRequestEntity();
        entity = new JSONObject();
        entity.put("user_id", 1);
        entity.put("first_name", "");
        entity.put("last_name", "");
        entity.put("tutor_id", 3);
        entity.put("course_id", "1");
        entity.put("request_date", "2021-01-01 00:00:00");
        entity.put("start_time", "2021-02-01 00:00:00");
        entity.put("endtime", "2021-03-01 00:00:00");
        entity.put("balance", "5.5");
        entity.put("approved", 1);
        entity.put("tutor_full_name", "Ismail Corneliussen");
        entity.put("user_full_name", "Leon Olivier");
        entity.put("course_name", "Object-oriented Programming");
    }

    @Test (expected = NullPointerException.class)
    public void checkNullConstructor1() throws JSONException, ParseException {
        e.fromJson(null);
    }

    @Test
    public void RealTutorRequestEntity() throws JSONException, ParseException {
        e.fromJson(entity);
        assertEquals(0, e.getUserId());
    }

    @Test
    public void RealTutorRequestEntity1() throws JSONException, ParseException {
        e.fromJson(entity);
        assertEquals(0, e.getTutorId());
    }

    @Test
    public void RealTutorRequestEntity2() throws JSONException, ParseException {
        e.fromJson(entity);
        assertEquals(null, e.getCourseId());
    }

    @Test
    public void RealTutorRequestEntity3() throws JSONException, ParseException {
        e.fromJson(entity);
        assertEquals(0, e.getApproved());
    }

    @Test
    public void RealTutorRequestEntity4() throws JSONException, ParseException {
        e.fromJson(entity);
        assertEquals(null, e.getDate());
    }

    @Test
    public void RealTutorRequestEntity5() throws JSONException, ParseException {
        e.fromJson(entity);
        assertEquals(0, e.getApproved());
    }

    @Test
    public void checkNullDownloadJSON() {
        r.downloadJSON(null);
    }

    @Test
    public void DownloadJSON() {
        r.downloadJSON("http://coms-510-04.cs.iastate.edu/tutor_requests.php");
    }
}