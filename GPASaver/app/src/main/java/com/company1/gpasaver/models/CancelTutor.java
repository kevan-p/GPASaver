package com.company1.gpasaver.models;

public class CancelTutor {
    private User tutor;
    private Course course;
    
    public CancelTutor(User tutor, Course course) {
        this.tutor = tutor;
        this.course = course;
    }
    
    public User getTutor() { return this.tutor;}
    public Course getCourse() {return this.course;}
}
