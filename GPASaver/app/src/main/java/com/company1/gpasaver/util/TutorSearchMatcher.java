package com.company1.gpasaver.util;

import com.company1.gpasaver.models.User;

public class TutorSearchMatcher {

    public boolean validateUser(User user, String targetClassName, String targetTutorName) {

        boolean containsFullName = user.firstName.toLowerCase().contains(targetTutorName.toLowerCase());
        if(!containsFullName){
            return false;
        }
        if(targetClassName.equals("Show All")){
            return true;
        }
        if(user.subjects_will_tutor.contains(targetClassName)) {
            return true;
        }
        return false;
    }
}
