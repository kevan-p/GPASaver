package com.company1.gpasaver.models;

public class TutorRequest {
    public boolean accepted;

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public TutorRequest(User from, User to) {
        if (from == null || to == null)
            throw new IllegalStateException("arguments cannot be null");
        this.from = from;
        this.to = to;
    }

    private User from;
    private User to;
}
