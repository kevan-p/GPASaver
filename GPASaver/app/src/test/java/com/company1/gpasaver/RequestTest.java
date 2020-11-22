package com.company1.gpasaver;

import com.company1.gpasaver.models.TutorRequest;
import com.company1.gpasaver.models.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    TutorRequest tutorRequest;

    @Test (expected = IllegalStateException.class)
    public void checkNullConstructor1(){
        tutorRequest = new TutorRequest(new User(), null);
    }

    @Test (expected = IllegalStateException.class)
    public void checkNullConstructor2(){
        tutorRequest = new TutorRequest(null, new User());
    }

    @Test
    public void checkGetter1(){
        tutorRequest = new TutorRequest(new User().setFirstName("name"), new User().setFirstName("name2"));
        assertEquals("name", tutorRequest.getFrom().getFirstName());
    }

    @Test
    public void checkGetter2(){
        tutorRequest = new TutorRequest(new User().setFirstName("name"), new User().setFirstName("name2"));
        assertEquals("name2", tutorRequest.getTo().getFirstName());
    }
}

