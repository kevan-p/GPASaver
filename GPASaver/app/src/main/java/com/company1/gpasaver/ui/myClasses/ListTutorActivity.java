package com.company1.gpasaver.ui.myClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.Tutor;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.profile.ProfileView_Real;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListTutorActivity extends AppCompatActivity {

    public String getTutors = "http://coms-510-04.cs.iastate.edu:80/getTutorsForCourse.php?courseid=";
    private TextView title;
    private ListView tutorList;
    TutorListAdapter adapter;
    private ArrayList<Tutor> tutors;
    private String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tutor);

        title = (TextView) findViewById(R.id.class_name);
        tutorList = (ListView) findViewById(R.id.class_tutor_list);
        tutors = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            title.setText(bundle.getString("course_name"));
            courseId = bundle.getString("course_id");

            populateTutorList(() ->
            {
                adapter = new TutorListAdapter(getApplicationContext(), R.layout.item_tutor_list, tutors);
                tutorList.setAdapter(adapter);

                tutorList.setOnItemClickListener((adapterView, view, i, l) ->
                {
                    String id = tutors.get(i).getId();
                    String name = tutors.get(i).getName();
                    String phone = tutors.get(i).getPhone();
                    String email = tutors.get(i).getEmail();
                    User user = new User(Integer.valueOf(id), name);
                    user.setPhoneNumber(phone);
                    user.setEmail(email);

                    Intent intent = new Intent(getApplicationContext(), ProfileView_Real.class);
                    intent.putExtra("serialize_tutor", user);
                    startActivity(intent);
                });
            });
        }
    }

    private void populateTutorList(final VolleyCallBack callBack)
    {
        String getCoursesURL = getTutors + courseId;
        JsonArrayRequest getRequest = new JsonArrayRequest
                (Request.Method.GET, getCoursesURL, null, response ->
                {
                    for(int i=0; i<response.length(); i++)
                    {
                        try {
                            JSONObject obj = (JSONObject) response.get(i);
                            String tutorId = obj.get("tutor_id").toString();
                            String tutorName = obj.get("full_name").toString();
                            String tutorPhone = obj.get("phone").toString();
                            String tutorEmail = obj.get("email").toString();
                            tutors.add(new Tutor(tutorName, tutorId, tutorPhone, tutorEmail));
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(getRequest);
    }

}