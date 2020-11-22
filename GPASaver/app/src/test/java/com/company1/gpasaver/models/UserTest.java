package com.company1.gpasaver.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test (expected = NullPointerException.class)
    public void shouldBeAbleToCreateWithEmptyJson() throws JSONException {
        User user = User.fromJsonObject(null);

        assertNull(user.firstName);
        assertNull(user.email);
        assertNull(user.phoneNumber);
        assertEquals(user.subjects_tutored.size(), 0);
        assertNull(user.picture);
        assertNull(user.GPA);
        assertNull(user.rate);
    }
}
