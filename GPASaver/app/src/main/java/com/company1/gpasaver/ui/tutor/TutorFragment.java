package com.company1.gpasaver.ui.tutor;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.company1.gpasaver.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TutorFragment extends Fragment{
    static ArrayAdapter<String> dataAdapter;
    private TutorViewModel tutorViewModel;
    public static String[] courses;
    String[] spinnerValue = {"CS510", "CS641"};
    Spinner spinnerView;
    String firstName, email, major, school, lastName;
    Double gpa, hour ;
    int id,tutor_id;
    EditText mFirstName, mLastName, mSchool, mMajor, mEmail, mHour, mGPA;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tutorViewModel = ViewModelProviders.of(this).get(TutorViewModel.class);
        View root = inflater.inflate(R.layout.activity_tutor, container, false);
        Button mSendFeedback = root.findViewById(R.id.btnSubmitTutor);
        //mFirstName = root.findViewById(R.id.editFirstName);
        //mLastName = root.findViewById(R.id.editLastName);
        //mSchool = root.findViewById(R.id.editSchool);
        //mEmail = root.findViewById(R.id.editEmail);
        //mMajor = root.findViewById(R.id.editMajor);
        mGPA = root.findViewById(R.id.editGPA);
        mHour = root.findViewById(R.id.editRate);
        spinnerView =(Spinner)root.findViewById(R.id.spinnerCourse);
        downloadJSON("http://coms-510-04.cs.iastate.edu/courseRequest.php");
        //spinnerView.setAdapter(dataAdapter);
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGPA.getText().toString().isEmpty()||
                         mHour.getText().toString().isEmpty()) { //EditText is empty
                    Toast.makeText(getActivity(), "Please fill in mandatory text box(es)", Toast.LENGTH_LONG).show();
                } else {
                    getData();
                    insertData();
                    Toast.makeText(getActivity(), "Thank you for sharing your data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final TextView textView = root.findViewById(R.id.textTutor);
        tutorViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void getData()
    {
        gpa = Double.parseDouble(mGPA.getText().toString());
        hour = Double.parseDouble((mHour.getText().toString()));
        String test=spinnerView.getSelectedItem().toString();

        id=Integer.parseInt((spinnerView.getSelectedItem().toString()).split("\\s+")[((spinnerView.getSelectedItem().toString()).split("\\s+")).length-1]);
        tutor_id=2;

    }
    private void insertData() {
        class InsertData extends AsyncTask<Void, Void, String>
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();

            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String databaseLink = "http://coms-510-04.cs.iastate.edu/postTutorRequest.php?" +
                            "id=" + tutor_id +
                            "&gpa=" + gpa +
                            "&hour=" + hour+
                            "&course="+ id;
                    String data = URLEncoder.encode("firstName", "UTF-8") + "=" +
                            URLEncoder.encode(firstName, "UTF-8");

                    data += "&" + URLEncoder.encode("lastName", "UTF-8") + "=" +
                            URLEncoder.encode(lastName, "UTF-8");

                    data += "&" + URLEncoder.encode("school", "UTF-8") + "=" +
                            URLEncoder.encode(school, "UTF-8");

                    data += "&" + URLEncoder.encode("major", "UTF-8") + "=" +
                            URLEncoder.encode(major, "UTF-8");

                    data += "&" + URLEncoder.encode("gpa", "UTF-8") + "=" +
                            URLEncoder.encode(String.valueOf(gpa), "UTF-8");

                    data += "&" + URLEncoder.encode("hour", "UTF-8") + "=" +
                            URLEncoder.encode(String.valueOf(hour), "UTF-8");

                    URL url = new URL(databaseLink);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    Toast.makeText(getActivity().getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                    return sb.toString();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        InsertData insertDataObj = new InsertData();
        insertDataObj.execute();
    }
    private void downloadJSON(final String urlWebService) {
        class DownloadJSON extends AsyncTask<Void, Void, String>
        {
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
                    int a=con.getResponseCode();
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
        TutorFragment.courses = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            TutorFragment.courses[i] = obj.getString("course_name") + " " + obj.getString("course_id");
        }
        dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,courses );

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(dataAdapter);
    }



}
