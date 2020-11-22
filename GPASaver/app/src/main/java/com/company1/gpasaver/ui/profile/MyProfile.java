package com.company1.gpasaver.ui.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.UserView.UserListViewAdapter;
import com.company1.gpasaver.ui.profile.ProfileView_Real;
import com.company1.gpasaver.ui.tutor.TutorFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MyProfile extends Fragment {
    static ArrayAdapter<String> dataAdapter;
    protected String email;
    protected ProgressDialog pDialog;
    Spinner spinnerView;
    protected ArrayList<User> user = new ArrayList<>();
    protected UserListViewAdapter arrayAdapter;
    protected ListView listView;
    protected RadioButton check_GPA;
    protected  RadioButton check_Rate;

    protected String users_list = "http://coms-510-04.cs.iastate.edu:80/get_tutor.php";
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TypefaceProvider.registerDefaultIconSets();
        root = inflater.inflate(R.layout.activity_my_profile, container, false);



        user.add(MySingleton.mainUser);
        listView = (ListView) root.findViewById(R.id.listview);
        arrayAdapter = new UserListViewAdapter(getContext(), R.layout.item_tutor, user);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // First get the user
                User tutor = user.get(i);
                System.out.println("x");
                // send to dialog
                Intent startIntent = new Intent(getContext(), EditProfile.class);
                startIntent.putExtra("serialize_tutor", tutor);
                startActivity(startIntent);
            }
        });

        return root;
    }




}
