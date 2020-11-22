package com.company1.gpasaver.models;

public class Availability {

    private Course course;
    private String time;

    public Availability(Course course, String time) {
        this.course = course;
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
