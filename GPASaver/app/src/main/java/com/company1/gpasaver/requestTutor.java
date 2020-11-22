package com.company1.gpasaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class requestTutor extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_USERID = "userid";
    private static final String KEY_TUTORID = "tutorid";
    private static final String KEY_COURSEID = "courseid";
    private static final String KEY_DATE = "date";
    private static final String KEY_STARTTIME = "ftime";
    private static final String KEY_ENDTIME = "etime";
    private static final String KEY_COST = "cost";
    private static final String KEY_MESSAGE = "message";
    private String request_tutor_url = "http://coms-510-04.cs.iastate.edu:80/request_tutor.php";

    static EditText userName, courseName, tutorName, startDate, fromTime, endTime;
    static TextView tvCost;
    static User user, tutor;
    static Course course;
    protected int hourly_rate;
    private float cost;
    private String availability;
    static Boolean isFromTime;
    static String ft,tt = "";
    static String fromDate, fromTimeStr, toTimeStr = "";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_tutor);
        //address all text/edit view
        userName = findViewById(R.id.editUserID);
        userName.setEnabled(false);
        courseName = findViewById(R.id.editCourseID);
        courseName.setEnabled(false);
        tutorName = findViewById(R.id.editTutorID);
        tutorName.setEnabled(false);
        startDate = findViewById(R.id.etStartDate);
        fromTime = findViewById(R.id.etStartTime);
        endTime = findViewById(R.id.etEndTime);
        Button btnCost = findViewById(R.id.btnCalculateCost);
        tvCost = findViewById(R.id.tvShowCost);
        Button btnSubmitRequest = (Button) findViewById(R.id.btnSubmitRequestTutor);
        //get info from previous page
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        tutor = (User) intent.getSerializableExtra("tutor");
        course = (Course) intent.getSerializableExtra("course");
        availability = intent.getStringExtra("availability");
        userName.setText(user.getFirstName());
        courseName.setText(course.getName());
        tutorName.setText(tutor.getFirstName());
        try {
            hourly_rate = Integer.parseInt(tutor.getRate());
        }
        catch (NumberFormatException e)
        {
            hourly_rate = 10;
        }
        ft = "";
        tt = "";
        fromDate = "";
        fromTimeStr = "";
        toTimeStr = "";


        //set date picker
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showPicker(v);
                    Toast.makeText(getApplicationContext(),
                            "Tutor's available Time: " + availability, Toast.LENGTH_LONG).show();
                }
            }
        });
        //set time picker
        fromTime.setInputType(InputType.TYPE_NULL);
        fromTime.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFromTime = true;
                    showtimePicker(v);
                    Toast.makeText(getApplicationContext(),
                            "Tutor's available Time: " + availability, Toast.LENGTH_LONG).show();
                }
            }
        });
        endTime.setInputType(InputType.TYPE_NULL);
        endTime.setOnFocusChangeListener (new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    isFromTime = false;
                    showtimePicker(v);
                    Toast.makeText(getApplicationContext(),
                            "Tutor's available Time: " + availability, Toast.LENGTH_LONG).show();
                }
            }
        });
        //set cost button onClick
        btnCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInputs()) { //EditText is empty
                    Toast.makeText(getApplicationContext(), "Please fill in mandatory text box(es)", Toast.LENGTH_LONG).show();
                }
                else {
                    cost = calculateCost();
                    if(cost>0) {
                        tvCost.setText(Float.toString(cost));

                        /* added account check*/
                        availableAccount accountCheck = new availableAccount();
                        accountCheck.getUserBalance();
                        if(!accountCheck.accountCheck()){ //unavailable account
                            Toast.makeText(getApplicationContext(), "Unavailable account", Toast.LENGTH_SHORT).show();
                        }
                        else if(!accountCheck.balanceCheck(cost)){ //balance not enough
                            Toast.makeText(getApplicationContext(), "Balance Not Enough", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please fill correct time", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        //set submit button onClick
        btnSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateInputs()) { //EditText is empty
                    Toast.makeText(getApplicationContext(), "Please fill in mandatory text box(es)", Toast.LENGTH_LONG).show();
                } else {
                    //send http request
                    new AlertDialog.Builder(requestTutor.this)
                            .setTitle("Request Tutor")
                            .setMessage("Are you sure you want to send this request?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    sendRequest();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_email)
                            .show();
                }
            }
        });
    }

    public void showtimePicker(View v) {
        DialogFragment tf = new TimePick();
        tf.show(this.getSupportFragmentManager(), "DatePicker");
    }
    public void showPicker(View v) {
        DialogFragment df = new DatePick();
        df.show(this.getSupportFragmentManager(), "DatePicker");
    }
    public static class DatePick extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calender = Calendar.getInstance();
            int year = calender.get(Calendar.YEAR);
            int month = calender.get(Calendar.MONTH);
            int day = calender.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            startDate.setText(year+"/"+ (month + 1) +"/" +dayOfMonth);
            fromDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        }
    }
    public static class TimePick extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calender = Calendar.getInstance();
            int hour = calender.get(Calendar.HOUR_OF_DAY);
            int minute = calender.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(isFromTime) {
                String meridianNess = (hourOfDay / 12 == 0 ? "AM" : "PM");
                int h = hourOfDay % 12;
                if (h == 0) h = 12;
                fromTime.setText(h + ":" + (minute < 10 ? "0" + minute : minute) + " " + meridianNess);
                ft = hourOfDay + ":" + minute;
                fromTimeStr = hourOfDay + ":" + minute + ":" + "00";
            }
            else {
                String meridianNess = (hourOfDay/12==0?"AM":"PM");
                int h = hourOfDay%12;
                if (h==0) h = 12;
                endTime.setText(h + ":" + (minute < 10 ? "0" + minute : minute) + " " + meridianNess);
                tt = hourOfDay + ":" + minute;
                toTimeStr = hourOfDay + ":" + minute + ":" + "00";
            }
        }
    }

    private void sendRequest() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERID, user.getId());
            request.put(KEY_TUTORID, tutor.getId());
            request.put(KEY_COURSEID, course.getId());
            request.put(KEY_DATE, fromDate);
            request.put(KEY_STARTTIME, fromDate + " " + fromTimeStr);
            request.put(KEY_ENDTIME, fromDate + " " + toTimeStr);
            request.put(KEY_COST, cost);
            Log.d("#####Request", request.toString());
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
                                new AlertDialog.Builder(requestTutor.this)
                                        .setTitle("Request Sent!")
                                        .setMessage("Please wait the tutor to approve it")
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .show();
                            }
                            else{
                                //show dialog
                                new AlertDialog.Builder(requestTutor.this)
                                        .setTitle("Failed to send this request")
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

    private void displayLoader() {
        pDialog = new ProgressDialog(requestTutor.this);
        pDialog.setMessage("Sending Request...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    protected boolean validateInputs() {
        return !(fromDate == ""
                || fromTimeStr == ""
                || toTimeStr == "");
    }


    protected float calculateCost() {
        int hourDiff = Integer.parseInt(tt.toString().split(":")[0]) - Integer.parseInt(ft.toString().split(":")[0]);
        int minDiff = Integer.parseInt(tt.toString().split(":")[1]) - Integer.parseInt(ft.toString().split(":")[1]);
        return (hourDiff + ((float)minDiff/60)) * hourly_rate;
    }

}