package com.company1.gpasaver.models;

public class TuteeModel {
    private User tutee;
    private User tutor;
    private String course;
    public boolean message;

    public User getTutee() {
        return tutee;
    }

    public User getTutor() {
        return tutor;
    }

    public String getCourse() {return course;}

    public TuteeModel(User tutee, User tutor) {
        if (tutee == null || tutor == null)
            throw new IllegalStateException("arguments cannot be null");
        this.tutor = tutee;
        this.tutee = tutee;
        this.course = course;

    }
}
