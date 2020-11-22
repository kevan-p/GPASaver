package com.company1.gpasaver.ui.requests;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.RealTutorRequestEntity;
import com.company1.gpasaver.requestTutor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyRequestsActivity extends AppCompatActivity {

    private static final String KEY_STATUS = "status";
    private static final String KEY_USERID = "userid";
    private static final String KEY_TUTORID = "tutorid";
    private static final String KEY_COURSEID = "courseid";
    private static final String KEY_DATE = "date";
    private static final String KEY_APPROVED = "approved";
    private static final String KEY_MESSAGE = "message";
    private String request_tutor_url = "http://coms-510-04.cs.iastate.edu:80/update_request_tutor.php";
    private String get_tutor_request_url = "http://coms-510-04.cs.iastate.edu/tutor_requests.php";

    private ListView _listView;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_requests);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this._listView = findViewById(R.id.request_list);

        setSupportActionBar(toolbar);

        this.downloadJSON(get_tutor_request_url);
    }

    public void downloadJSON(final String urlWebService) {
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    List<RealTutorRequestEntity> requests = new ArrayList<>();
                    for(int i = 0;i<jsonArray.length();i++){
                        requests.add(RealTutorRequestEntity.fromJson((JSONObject) jsonArray.get(i)));
                    }

                    List<RealTutorRequestEntity> filteredRequests = new ArrayList<>();
                    for(int i =0;i<requests.size();i++){
                        //filter out requests
                        //approved 0 means pending request, 1 means approved, and 2 means denied
                        if(requests.get(i).getTutorId() == MySingleton.mainUser.getId()
                                && requests.get(i).getApproved() == 0){
                            filteredRequests.add(requests.get(i));
                        }
                    }

                    ArrayAdapter adapter = new MyRequestsAdapter(MyRequestsActivity.this,
                            R.layout.real_request_model, filteredRequests);
                    _listView.setAdapter(adapter);
                    _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            new AlertDialog.Builder(MyRequestsActivity.this)
                                    .setTitle("Confirm")
                                    .setMessage("Please Make Your Decision")
                                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //1 means accepted
                                            updateRequest(filteredRequests.get(i), 1);
                                        }
                                    })
                                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //2 means denied
                                            updateRequest(filteredRequests.get(i), 2);
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_info)
                                    .show();
                        }

                    });
                } catch (JSONException | ParseException e) {
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

    protected void updateRequest(RealTutorRequestEntity tutorRequest, int approved) {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERID, tutorRequest.getUserId());
            request.put(KEY_TUTORID, tutorRequest.getTutorId());
            request.put(KEY_COURSEID, tutorRequest.getCourseId());
            request.put(KEY_DATE, tutorRequest.getDate());
            request.put(KEY_APPROVED, approved);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, request_tutor_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            Log.d("onRequestResponse", response.getInt(KEY_STATUS) + "");
                            if (response.getInt(KEY_STATUS) == 0) {
                                //show dialog
                                new AlertDialog.Builder(MyRequestsActivity.this)
                                        .setTitle("Request Updated!")
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                downloadJSON(get_tutor_request_url);
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .show();
                            }
                            else{
                                //show dialog
                                new AlertDialog.Builder(MyRequestsActivity.this)
                                        .setTitle("Failed to update this request")
                                        .setMessage(response.getString(KEY_MESSAGE))
                                        .setPositiveButton(android.R.string.ok, null)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Log.d("onRequestError", error.toString());
                        Toast.makeText(getApplicationContext(),
                                "Failed to send request. Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    protected void displayLoader() {
        pDialog = new ProgressDialog(MyRequestsActivity.this);
        pDialog.setMessage("Updating Request...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

}