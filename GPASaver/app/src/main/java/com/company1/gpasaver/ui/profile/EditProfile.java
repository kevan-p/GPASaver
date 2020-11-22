package com.company1.gpasaver.ui.profile;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.company1.gpasaver.AddMyselfTutorActivity;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.requests.MyRequestsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfile extends AppCompatActivity {
    public User tutor;
    public String updateBalanceUrl = "http://coms-510-04.cs.iastate.edu:80/Update_balance.php";
    public String putAvailability = "http://coms-510-04.cs.iastate.edu:80/put_availability.php";
    public String getAvailability = "http://coms-510-04.cs.iastate.edu:80/get_availability.php";
    public String getCourses = "http://coms-510-04.cs.iastate.edu:80/get_tutor_courses.php?tutorid=";
    public EditText get_sms;
    public TextView amount;

    private ArrayList<Availability> availabilityList;
    private ArrayList<Course> courses;
    private ListView scheduleList;
    private AvailabilityListAdapter adapter;
    private Button btnUpdateSchedule;
    private EditText getCourseId;
    private EditText getNewTime;

    private Button btnAddTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        TextView name_text = (TextView) findViewById(R.id.show_name);
        name_text.setText(MainActivity.Mysig.mainUser.getFirstName());

        TextView mail_text = (TextView) findViewById(R.id.show_mail);
        mail_text.setText(MainActivity.Mysig.mainUser.getEmail());

        TextView number_text = (TextView) findViewById(R.id.show_number);
        number_text.setText(MainActivity.Mysig.mainUser.getPhoneNumber());

        amount = (TextView) findViewById(R.id.show_classes);
        amount.setText("Current Balance is: " + MainActivity.Mysig.mainUser.getBalance().toString() );

        TextView userRole = (TextView) findViewById(R.id.userRole);

        if(MainActivity.Mysig.mainUser.isTutor) {
            userRole.setText("Tutor");
        }else{
            userRole.setText("Student");
        }

        // availability portion
        /* ===================================================================== */
        scheduleList = (ListView) findViewById(R.id.schedule_list);
        availabilityList = new ArrayList<>();
        courses = new ArrayList<>();

        populateCourseList(() -> loadAvailability());

        btnUpdateSchedule = (Button) findViewById(R.id.btn_update_schedule);
        getCourseId = (EditText) findViewById(R.id.editCourseID);
        getNewTime = (EditText) findViewById(R.id.editCourseTime);
        btnUpdateSchedule.setOnClickListener(view -> updateSchedule());

        // add myself to tutor portion
        /* ===================================================================== */
        btnAddTutor = (Button) findViewById(R.id.addTutorButton);
        if(MainActivity.Mysig.mainUser.isTutor)
            btnAddTutor.setText("Tutor another course");
        else
            btnAddTutor.setText("Become a tutor");
        btnAddTutor.setOnClickListener(view ->
        {
            Intent myIntent = new Intent(EditProfile.this, AddMyselfTutorActivity.class);
            this.startActivity(myIntent);
        });
        /* ===================================================================== */

        Button send_SMS = (Button) findViewById(R.id.send_sms);
        get_sms = (EditText) findViewById(R.id.balance);
        get_sms.setHint("Current Balance is: " + MainActivity.Mysig.mainUser.getBalance().toString() );
        send_SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int balance = 0;
                try {
                    balance  = Integer.parseInt(get_sms.getText().toString());
                    System.out.println("x");
                } catch (NumberFormatException | NullPointerException nfe) {
                    get_sms.setError("Not a Valid Number");
                    get_sms.requestFocus();
                }
                JSONObject request = new JSONObject();
                try {
                    //Populate the request parameters
                    request.put("id", MainActivity.Mysig.mainUser.getId());
                    request.put("balance", balance+MainActivity.Mysig.mainUser.getBalance());

                } catch (JSONException e) {
//                    e.printStackTrace();
                }
                JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                        (Request.Method.POST, updateBalanceUrl, request, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("x");
                                get_sms.setError("updated successfully");

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("x");
                                //Display error message whenever an error occurs
                                Toast.makeText(getApplicationContext(),
                                        "Error", Toast.LENGTH_SHORT).show();

                            }
                        });

                MainActivity.Mysig.mainUser.setBalance(balance+MainActivity.Mysig.mainUser.getBalance());
                amount.setText("Current Balance is: " + MainActivity.Mysig.mainUser.getBalance().toString() );
                get_sms.setHint("Current Balance is: " + MainActivity.Mysig.mainUser.getBalance().toString() );
            }
        });
    }

    private void loadAvailability()
    {
        int userId = MainActivity.Mysig.mainUser.getId();
        for(int i=0; i<courses.size(); i++)
        {
            getAvailability(userId, courses.get(i), () ->
            {
                adapter = new AvailabilityListAdapter(getApplicationContext(), R.layout.item_availability, availabilityList);
                scheduleList.setAdapter(adapter);
                scheduleList.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
                    Intent intent = new Intent(getApplicationContext(), MyRequestsActivity.class);
                    startActivity(intent);
                });
            });
        }
    }

    private void updateSchedule()
    {
        String courseId = getCourseId.getText().toString();
        String time = getNewTime.getText().toString();

        if (courseId.isEmpty() || time.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Empty Value!",Toast.LENGTH_SHORT).show();
        }
        else if (!isValidId(courseId)) {
            Toast.makeText(getApplicationContext(),"Invalid Course!",Toast.LENGTH_SHORT).show();
        }
        else if (!isValidTime(time)) {
            Toast.makeText(getApplicationContext(),"Invalid Time!",Toast.LENGTH_SHORT).show();
        }
        else{
            int userId = MainActivity.Mysig.mainUser.getId();
            putAvailability(userId, courseId, time);
        }
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

    public boolean isValidId(String courseId)
    {
        if (courseId.isEmpty()) return false;
        int id_int = Integer.valueOf(courseId);
        return id_int > 0 && id_int < 15;
    }

    private void populateCourseList(final VolleyCallBack callBack)
    {
        Resources res = getResources();
        String[] courseNames = res.getStringArray(R.array.courses);
        String getCoursesURL = getCourses + MainActivity.Mysig.mainUser.getId();

        JsonArrayRequest getRequest = new JsonArrayRequest
                (Request.Method.GET, getCoursesURL, null, response ->
                {
                    for(int i=0; i<response.length(); i++)
                    {
                        try
                        {
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

    private void getAvailability(int tutorId, Course course, final VolleyCallBack callBack)
    {
        String URL = getAvailability + "?tutorid=" + tutorId + "&courseid=" + course.getId();
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest
                (Request.Method.GET, URL, null, response ->
                {
                    try
                    {
                        String time = "";
                        JSONObject obj = (JSONObject) response.get(0);
                        time = obj.get("availability").toString();
                        availabilityList.add(new Availability(course, time));
                    } catch (JSONException e) { e.printStackTrace(); }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    private void putAvailability(int tutorId, String courseId, String time)
    {
        String URL = putAvailability;
        URL += "?tutorid=" + tutorId;
        URL += "&courseid=" + courseId;
        URL += "&availability=" + time;

        StringRequest postRequest = new StringRequest
                (Request.Method.POST, URL, response ->
                {
                    if(response.equals("Successful"))
                        Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_SHORT).show();
                    if(response.contains("error"))
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(postRequest);
    }

}