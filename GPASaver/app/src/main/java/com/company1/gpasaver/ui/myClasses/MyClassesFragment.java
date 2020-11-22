package com.company1.gpasaver.ui.myClasses;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.profile.AvailabilityListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyClassesFragment extends Fragment {

    public String getCourses = "http://coms-510-04.cs.iastate.edu:80/Get_courses_enrolled.php?id=";
    private ListView classList;
    private ArrayList<Course> classes;
    ClassListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_classes,container,false);
        classList = (ListView) view.findViewById(R.id.class_list);
        classes = new ArrayList<>();

        populateCourseList(() ->
        {
            adapter = new ClassListAdapter(getContext(), R.layout.item_my_class, classes);
            classList.setAdapter(adapter);
            classList.setOnItemClickListener((adapterView, view1, i, l) ->
            {
                Intent intent = new Intent(getActivity(), ListTutorActivity.class);
                intent.putExtra("course_id", classes.get(i).getId());
                intent.putExtra("course_name", classes.get(i).getName());
                startActivity(intent);
            });
        });

        return view;
    }

    private void populateCourseList(final VolleyCallBack callBack)
    {
        String getCoursesURL = getCourses + MainActivity.Mysig.mainUser.getId();
        JsonArrayRequest getRequest = new JsonArrayRequest
                (Request.Method.GET, getCoursesURL, null, response ->
                {
                    for(int i=0; i<response.length(); i++)
                    {
                        try {
                            JSONObject obj = (JSONObject) response.get(i);
                            String courseId = obj.get("course_id").toString();
                            String courseName = obj.get("course_name").toString();
                            classes.add(new Course(courseId, courseName));
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(getContext()).addToRequestQueue(getRequest);
    }

}
