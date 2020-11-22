package com.company1.gpasaver.models;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RealTutorRequestEntity {

    public int userId;
    public String firstName;
    public String lastName;
    public String tutorFullName;
    public String userFullName;
    public int tutorId;
    public String courseId;
    public String courseName;
    public Date requestDate;
    public String date;
    public Date startTime;
    public Date endTime;
    public String balance;

    public int approved;

    public static RealTutorRequestEntity fromJson(JSONObject object) throws JSONException, ParseException {

        RealTutorRequestEntity entity = new RealTutorRequestEntity();
        entity.userId = object.getInt("user_id");
        entity.firstName = object.getString("first_name");
        entity.lastName = object.getString("last_name");
        entity.tutorFullName = object.getString("tutor_full_name");
        entity.userFullName = object.getString("user_full_name");
        entity.tutorId = object.getInt("tutor_id");
        entity.courseId = object.getString("course_id");
        entity.courseName = object.getString("course_name");
        entity.date = object.getString("request_date");
        @SuppressLint("SimpleDateFormat") Date requestDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(object.getString("request_date"));
        entity.requestDate = requestDate;
        @SuppressLint("SimpleDateFormat") Date startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(object.getString("start_time"));
        entity.startTime = startTime;
        @SuppressLint("SimpleDateFormat") Date endTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(object.getString("endtime"));
        entity.endTime = endTime;
        entity.balance = object.getString("balance");
        entity.approved = object.getInt("approved");

        return entity;
    }

    public int getUserId() {
        return userId;
    }

    public int getTutorId() {
        return tutorId;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getApproved() {
        return approved;
    }

    public String getDate() {
        return date;
    }

}
