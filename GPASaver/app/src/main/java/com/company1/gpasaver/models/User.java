package com.company1.gpasaver.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

// For randomuser.me API: https://randomuser.me/documentation#howto
@Entity(tableName = "users")
public class User implements Serializable {
    // Fields marked public for testing.
    //@SerializedName("id") public  String id;
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private int id;

    @ColumnInfo(name = "firstname")
    @SerializedName("firstName")
    public String firstName;

    @Ignore
    @SerializedName("password")
    public String password;
    @Ignore
    @SerializedName("lastName")
    public String lastName;
    @Ignore
    @SerializedName("phone")
    public String phoneNumber;
    @Ignore
    @SerializedName("image")
    public int image;
    @Ignore
    @SerializedName("gender")
    public String gender;
    @Ignore
    @SerializedName("name")
    public Name name;
    // @SerializedName("location") public Location location;
    @Ignore
    @SerializedName("email")
    public String email;
    @Ignore
    @SerializedName("login")
    public Login login;
    @Ignore
    @SerializedName("picture")
    public Picture picture;
    @Ignore
    @SerializedName("is_tutor")
    public Boolean isTutor;

    public String picture_string;

    public String GPA;
    public String rate;
    public double balance;

    @Ignore public String fullName;
    @Ignore public List<String> subjects_tutored = new ArrayList<>();
    @Ignore public List<String> subjects_will_tutor = new ArrayList<>();
    @Ignore public List<String> subjects_leanred = new ArrayList<>();
    @Ignore public List<String> subjects_will_learn = new ArrayList<>();

    public static User fromJsonObject(JSONObject object)  throws JSONException  {
        return new User()
                .setFirstName(object.getString("full_name"))
                .setEmail(object.getString("email"))
                .setPhoneNumber(object.getString("phone"))
                .addSubjectWillTutor(object.getString("course_name"))
                .setPicture(object.getString("image_url"))
                .setGPA(object.getString("GPA"))
                .setRate(object.getString("rate"))
                .setId(object.getInt("id"));
    }

    public boolean hasEmail() {
        return email != null && !email.isEmpty();
    }
    @Ignore
    public User() {

    }

    public User(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getGPA() {
        return GPA;
    }

    public User setGPA(String GPA) {
        this.GPA = GPA;
        return this;
    }

    public String getRate() {
        return rate;
    }

    public User setRate(String rate) {
        this.rate = rate;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setPicture(String picture) {
        this.picture_string = picture;
        return this;
    }

    public String getPicture() {
        return picture_string;
    }

    public User setIsTutor(int tutor) {
        this.isTutor = tutor == 1 ? true : false;
        return this;
    }

    public Boolean getIsTutor() {
        return isTutor;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setImage(int image) {
        this.image = image;
        return this;
    }

    public int getImage() {
        return image;
    }

    public User setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public User addSubjectWillTutor(String subject) {
        subjects_will_tutor.add(subject);
        return this;
    }

    public String getSubjectWillTutor() {
        StringBuilder sb = new StringBuilder();
        for (String s : subjects_will_tutor) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public User addSubjectTutored(String subject) {
        subjects_tutored.add(subject);
        return this;
    }

    public String getSubjectTutored() {
        StringBuilder sb = new StringBuilder();
        for (String s : subjects_tutored) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public User addSubjectLearned(String subject) {
        subjects_leanred.add(subject);
        return this;
    }

    public String getSubjectLearned() {
        StringBuilder sb = new StringBuilder();
        for (String s : subjects_leanred) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public User addSubjectWillLearn(String subject) {
        subjects_will_learn.add(subject);
        return this;
    }

    public String getSubjectWillLearn() {
        StringBuilder sb = new StringBuilder();
        for (String s : subjects_will_learn) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public String getName() {
        //Don't use this. In database there is only full name.
        //Check LoginActivity line 158 MUser.setFirstName(response.getString("full_name"));
        //first name is full name....
        return firstName + " " + lastName;
    }
}
