package com.company1.gpasaver;

import com.company1.gpasaver.models.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class TutorTest {
    User tutorUser;

    @Test
    public void checkIsTutor1(){
        tutorUser = new User().setIsTutor(1);
        assertEquals(true, tutorUser.getIsTutor());
    }

    @Test
    public void checkIsTutor2(){
        tutorUser = new User().setIsTutor(0);
        assertEquals(false, tutorUser.getIsTutor());
    }
}
