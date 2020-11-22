
package com.company1.gpasaver;

import android.app.Activity;
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
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.UserView.UserListViewAdapter;
import com.company1.gpasaver.ui.profile.ProfileView_Real;
import com.company1.gpasaver.ui.tutor.TutorFragment;
import com.company1.gpasaver.util.TutorSearchMatcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class AdminViewActivity extends Fragment {
    static ArrayAdapter<String> dataAdapter;
    protected String email;
    protected ProgressDialog pDialog;
    Spinner spinnerView;
    EditText textView;
    protected ArrayList<User> user = new ArrayList<>();
    protected UserListViewAdapter arrayAdapter;
    protected ListView listView;
    protected RadioButton check_GPA;
    protected  RadioButton check_Rate;

    protected String users_list = "http://coms-510-04.cs.iastate.edu:80/get_all.php";
    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TypefaceProvider.registerDefaultIconSets();
        root = inflater.inflate(R.layout.activity_find_tutor, container, false);

        check_GPA = root.findViewById(R.id.GPA_Check);
        check_Rate = root.findViewById(R.id.RATE_Check);
        check_GPA.toggle();
        check_GPA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check_Rate.setChecked(false);
                }
            }
        });
        check_Rate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check_GPA.setChecked(false);
                }
            }
        });


        listView = (ListView) root.findViewById(R.id.listview);
        spinnerView = (Spinner) root.findViewById(R.id.spinnerCourse);
        textView = (EditText) root.findViewById(R.id.name_text);

        downloadJSON("http://coms-510-04.cs.iastate.edu/courseRequest.php");
        arrayAdapter = new UserListViewAdapter(getContext(), R.layout.item_tutor, user);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // First get the user
                User tutor = user.get(i);
                // send to dialog
                Intent startIntent = new Intent(getContext(), ProfileView_Real.class);
                startIntent.putExtra("serialize_tutor", tutor);
                startActivity(startIntent);
            }
        });

        BootstrapButton refresh = root.findViewById(R.id.btnRefresh);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_users();
                // listView.setAdapter(arrayAdapter);

            }
        });
        return root;
    }


    /**
     * Display Progress bar while registering
     */
    private void displayLoader() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Getting users.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void get_users() {
        displayLoader();
        downloadJSON1(users_list);
    }

    private void downloadJSON(final String urlWebService) {
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadintoSpinnerView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    int a = con.getResponseCode();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadintoSpinnerView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        TutorFragment.courses = new String[jsonArray.length() + 1];
        TutorFragment.courses[0] = "Show All";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            TutorFragment.courses[i + 1] = obj.getString("course_name");
        }
        dataAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_spinner_item, TutorFragment.courses);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(dataAdapter);
    }

    private void downloadJSON1(final String urlWebService) {
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadintoUser(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    int a = con.getResponseCode();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");

                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadintoUser(String json) throws JSONException {
        user.clear();
        String class_name = spinnerView.getSelectedItem().toString();
        String name_text = textView.getText().toString();

        JSONArray jsonArray = new JSONArray(json);
        TutorFragment.courses = new String[jsonArray.length()];

        TutorSearchMatcher tutorSearchMatcher = new TutorSearchMatcher();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            User currentUser = User.fromJsonObject(object);

            if(tutorSearchMatcher.validateUser(currentUser, class_name, name_text)) {
                user.add(User.fromJsonObject(object));
            }
        }
        boolean gpaChecker = check_GPA.isChecked();
        pDialog.dismiss();
        arrayAdapter.notifyDataSetInvalidated();
        listView.invalidateViews();
    }
/*
    private void sort(ArrayList<User> Array, boolean sortGPA) {
        int i, j;

        for (i = 1; i < Array.size(); i++) {
            User key = Array.get(i);
            j = i;

            if(sortGPA){
                while ((j > 0) && (Double.valueOf(Array.get(j - 1).GPA) < Double.valueOf(key.GPA))) {
                    Array.set(j, Array.get(j - 1));
                    j--;
                }
            }else{
                while ((j > 0) && (Double.valueOf(Array.get(j - 1).rate) > Double.valueOf(key.rate))) {
                    Array.set(j, Array.get(j - 1));
                    j--;
                }
            }
            Array.set(j, key);
        }
    }
    */
}
