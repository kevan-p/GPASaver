package com.company1.gpasaver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public  class MainUser extends AppCompatActivity {
        private String name;
        private String email;
        private String password;
        private String created_at;
        private String newPassword;
        private String token;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        User current = new User()
//        session = new SessionHandler(getApplicationContext());
//
//        if(session.isLoggedIn()){
//            loadDashboard();
//        }
    }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

