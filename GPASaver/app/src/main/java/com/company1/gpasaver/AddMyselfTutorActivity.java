package com.company1.gpasaver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapDropDown;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.review.RateFromProfileActivity;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddMyselfTutorActivity extends AppCompatActivity
{
    public String BASE_URL = "http://coms-510-04.cs.iastate.edu:80/add_myself_tutor.php";
    String server_response;

    EditText nameTxt;
    EditText gpaTxt;
    EditText availabilityTxt;
    BootstrapDropDown spin;
    BootstrapButton submitBtn;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_myself_tutor);

        nameTxt = (EditText) findViewById(R.id.etTutorName);
        gpaTxt = (EditText) findViewById(R.id.etGpa);
        availabilityTxt = (EditText) findViewById(R.id.etAvailability);
        spin = (BootstrapDropDown) findViewById(R.id.course_dropdown);
        submitBtn = (BootstrapButton) findViewById(R.id.btnAddTutor);

        nameTxt.setText(MainActivity.Mysig.mainUser.getFirstName());

        spin.setOnDropDownItemClickListener(new BootstrapDropDown.OnDropDownItemClickListener()
        {
            @Override
            public void onItemClick(ViewGroup parent, View v, int id)
            {
                String[] dropDownItems = spin.getDropdownData();
                String courseSelected = dropDownItems[id];
                spin.setText(courseSelected);
                course = new Course(String.valueOf(id+1), courseSelected);
//                Toast.makeText(getApplicationContext(), courseSelected, Toast.LENGTH_LONG).show();
            }
        });

        submitBtn.setOnClickListener(view ->
        {
            if (course == null){
                Toast.makeText(AddMyselfTutorActivity.this, "Please select a course", Toast.LENGTH_LONG).show();
            }
            else if (gpaTxt.getText().toString().isEmpty()) { // gpa is empty
                Toast.makeText(AddMyselfTutorActivity.this, "Please provide your GPA for this course", Toast.LENGTH_LONG).show();
            }
            else if (availabilityTxt.getText().toString().isEmpty()){ // availability is empty
                Toast.makeText(AddMyselfTutorActivity.this, "Please provide your availability", Toast.LENGTH_LONG).show();
            }
            else if(isValidGpa(gpaTxt.getText().toString()) && isValidTime(availabilityTxt.getText().toString()))
            {
                String userId = String.valueOf( MainActivity.Mysig.mainUser.getId() );
                String name = nameTxt.getText().toString();
                String gpa = gpaTxt.getText().toString();
                String availability = availabilityTxt.getText().toString();
                submitTutor(userId, gpa, availability, ()->sendRespond());    // send out
            }
            else
            {
                Toast.makeText(AddMyselfTutorActivity.this, "Please verify your inputs", Toast.LENGTH_LONG).show();
            }
        });

    }

    // invoked upon callback succeed
    private void sendRespond()
    {
        Toast.makeText(AddMyselfTutorActivity.this, server_response, Toast.LENGTH_LONG).show();
        if(server_response.contains("Successful"))
            finish(); // close this activity if successful
    }

    private void submitTutor(String tutorId, String gpa, String availability, final VolleyCallBack callBack)
    {
        //http://coms-510-04.cs.iastate.edu:80/add_myself_tutor.php?courseid=12&tutorid=1&gpa=3.6&availability=MF 3AM-5AM
        String URL = BASE_URL;
        URL += "?courseid=" + course.getId();
        URL += "&tutorid=" + tutorId;
        URL += "&gpa=" + gpa;
        URL += "&availability=" + availability;

        StringRequest postRequest = new StringRequest
                (Request.Method.POST, URL, response ->
                {
                    if(response.equals("successful"))
                        server_response = "Successful, thank you for your submission!";
                    else if(response.equals("exists"))
                        server_response = "unsuccessful, you're already a tutor for this class!";
                    else
                        server_response = "unsuccessful, please try again!";

                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(postRequest);
    }

    public boolean isValidGpa(String gpa)
    {
        if(gpa == null) return false;
        float gpa_num = 0;
        try{
            gpa_num = Float.parseFloat(gpa);
        } catch (Exception e) {
            return false;
        }

        if(gpa_num < 0 || gpa_num > 4) return false;

        return true;
    }

    public boolean isValidTime(String time)
    {
        boolean validDate = false;
        boolean validTime = false;

        if (!time.contains(" ") || !time.contains("-")) return false;

        String[] segment = time.split(" ");
        if(segment.length != 2) return false;
        if(segment[0].length()>5) return false;

        Matcher m0 = Pattern.compile("[MTWRF]*").matcher(segment[0]);
        if (m0.find()) {
            validDate = true;
        }

        String[] segmentTime = time.split("-");
        if(segmentTime.length != 2) return false;
        Matcher m1 = Pattern.compile("([01]?[0-9])([AaPp][Mm])").matcher(segmentTime[0]);
        Matcher m2 = Pattern.compile("([01]?[0-9])([AaPp][Mm])").matcher(segmentTime[1]);
        if (m1.find() && m2.find()) {
            validTime = true;
        }

        return validDate && validTime;
    }

}