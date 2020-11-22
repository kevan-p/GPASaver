package com.company1.gpasaver;

import com.company1.gpasaver.models.Location;
import com.company1.gpasaver.models.Login;
import com.company1.gpasaver.models.Picture;
import com.company1.gpasaver.models.User;
import java.util.ArrayList;
import java.util.List;

public class FakeUserGenerator {
    // TODO: get data from our own API
    private static final String USER_ROLE_STUDENT = "student";
    private static final String USER_ROLE_TUTOR = "tutor";
    private static final String USER_FIRST_NAME = "Jon";
    private static final String USER_LAST_NAME = "Snow";

    private static final String USER_PHONE_TEST = "555-230-4567";
    private static final String USER_EMAIL_TEST = "jonsnow@example.com";
    private static final String USER_PICTURE_TEST = "http://api.randomuser.me/portraits/women/39.jpg";
    private static final String USER_STREET_TEST = "9193 brock rd";
    private static final String USER_CITY_TEST = "flatrock";
    private static final String USER_STATE_TEST = "Iowa";

    public static List<User> getUserList() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(getUsers());
        }
        return users;
    }

    public static User getUsers() {
        User user = new User();
        user.picture = new Picture();
        user.picture = new Picture();
        //user.location = new Location();
        user.login = new Login();
        user.fullName = USER_FIRST_NAME + " " + USER_LAST_NAME;
        user.email = USER_EMAIL_TEST;
        user.picture.large = USER_PICTURE_TEST;
        //user.location.street = USER_STREET_TEST;
       // user.location.city = USER_CITY_TEST;
       // user.location.state = USER_PHONE_TEST;
        return user;

    }

}
