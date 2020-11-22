package com.company1.gpasaver.ui.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.requestTutor;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.review.RateFromProfileActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProfileView_Real  extends AppCompatActivity {

    public String getAvailability = "http://coms-510-04.cs.iastate.edu:80/get_availability.php";
    public String getCourses = "http://coms-510-04.cs.iastate.edu:80/get_tutor_courses.php?tutorid=";
    private ArrayList<Availability> availabilityList;
    private ArrayList<Course> courses;
    private ListView scheduleList;
    private AvailabilityListAdapter adapter;
    private User tutor;
    private Button rateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_viewer);
        tutor = (User) getIntent().getSerializableExtra("serialize_tutor");

        TextView name_text = (TextView) findViewById(R.id.show_name);
        name_text.setText(tutor.getFirstName());

        TextView mail_text = (TextView) findViewById(R.id.show_mail);
        mail_text.setText(tutor.getEmail());

        TextView number_text = (TextView) findViewById(R.id.show_number);
        number_text.setText(tutor.getPhoneNumber());

        TextView userRole = (TextView) findViewById(R.id.userRole);
        userRole.setText("Tutor");

        // tutor's availability
        /* ===================================================================== */
        scheduleList = (ListView) findViewById(R.id.schedule_list);
        availabilityList = new ArrayList<>();
        courses = new ArrayList<>();
        populateCourseList(() -> loadAvailability());
        /* ===================================================================== */



        // tutor's rate
        /* ===================================================================== */
        rateBtn = (Button) findViewById(R.id.profileRateButton);


        rateBtn.setOnClickListener(view ->
        {
            Intent myIntent = new Intent(ProfileView_Real.this, RateFromProfileActivity.class);
            myIntent.putExtra("serialize_tutor", tutor);
            this.startActivity(myIntent);
        });

        /* ===================================================================== */


        int permissionCode = 1;
        String[] permission = {Manifest.permission.SEND_SMS};
        if (ActivityCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permission, permissionCode);
        }

        Button send_SMS = (Button) findViewById(R.id.send_sms);
        send_SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText get_sms = (EditText) findViewById(R.id.sms);
                String sms = get_sms.getText().toString();
                Boolean sent_successful = sendSMS(sms, tutor.getPhoneNumber());
                if (sent_successful) {
                    get_sms.getText().clear();
                }
            }
        });

    }

    public Boolean sendSMS(String sms, String tutor_number) {
        Boolean sent = false;
        SmsManager sm = SmsManager.getDefault();

        try {
            sm.sendTextMessage(tutor_number, null, sms, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent Successfully!", Toast.LENGTH_SHORT).show();
            sent = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS Failed to Sent.", Toast.LENGTH_SHORT).show();
        }
        return sent;
    }

    public int findTutorByName(String name, String[] tutors)
    {
//        Resources res = getResources();
//        String[] tutors = res.getStringArray(R.array.tutors);
        for(int i=0; i<tutors.length; i++)
        {
            if(tutors[i].trim().equals(name.trim()))
                return i+1;
        }
        return 0;
    }

    private void populateCourseList(final VolleyCallBack callBack)
    {
        Resources res = getResources();
        String[] courseNames = res.getStringArray(R.array.courses);

        String getCoursesURL = getCourses + tutor.getId();
        JsonArrayRequest getRequest = new JsonArrayRequest
                (Request.Method.GET, getCoursesURL, null, response ->
                {
                    for(int i=0; i<response.length(); i++)
                    {
                        try {
                            JSONObject obj = (JSONObject) response.get(i);
                            String courseId = obj.get("course_id").toString();
                            String courseName = "";
                            int cId = Integer.valueOf(courseId)-1;
                            if(courseNames[cId] != null)
                                courseName = courseNames[cId];
                            else
                                courseName = "Course#" + courseId;
                            Course course = new Course(courseId, courseName);
                            courses.add(course);
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(getRequest);
    }

    private void loadAvailability()
    {
        int tutorId = tutor.getId();
        for(int i=0; i<courses.size(); i++)
        {
            getAvailability(tutorId, courses.get(i), () ->
            {
                adapter = new AvailabilityListAdapter(getApplicationContext(), R.layout.item_availability, availabilityList);
                scheduleList.setAdapter(adapter);
                scheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        //a tutor cannot request himself as a tutor. So check here
                        if (tutorId != MainActivity.Mysig.mainUser.getId()) {
                            Intent i = new Intent(getApplicationContext(), requestTutor.class);
                            i.putExtra("user", MainActivity.Mysig.mainUser);
                            i.putExtra("course", availabilityList.get(arg2).getCourse());
                            i.putExtra("tutor", tutor);
                            i.putExtra("availability", availabilityList.get(arg2).getTime());
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "Cannot Request Yourself!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            });
        }
    }

    private void getAvailability(int tutorId, Course course, final VolleyCallBack callBack)
    {

        String URL = getAvailability + "?tutorid=" + tutorId + "&courseid=" + course.getId();
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (Request.Method.GET, URL, null, response ->
                {
                    try {
                        String time = "";
                        JSONObject obj = (JSONObject) response.get(0);
                        time = obj.get("availability").toString();
                        availabilityList.add(new Availability(course, time));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

}