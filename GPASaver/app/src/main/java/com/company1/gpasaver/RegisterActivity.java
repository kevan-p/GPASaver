package com.company1.gpasaver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.beardedhen.androidbootstrap.BootstrapButton;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class RegisterActivity extends AppCompatActivity {
  private static final String KEY_STATUS = "status";
  private static final String KEY_MESSAGE = "message";
  private static final String KEY_FULL_NAME = "name";
  private static final String KEY_PASSWORD = "password";
  private static final String KEY_EMAIL = "email";
  private static final String KEY_EMPTY = "";
  protected EditText etUsername;
  protected EditText etPassword;
  protected EditText etConfirmPassword;
  protected EditText etFullName;
  protected EditText etEmail;
  protected String username;
  protected String password;
  protected String confirmPassword;
  protected String fullName;
  protected String email;
  protected ProgressDialog pDialog;
  //private String register_url = "http://10.0.2.2:8080/register";
 // protected String register_url = "http://coms-510-04.cs.iastate.edu:8080/register";
  protected String register_url = "http://coms-510-04.cs.iastate.edu:80/registration.php";
  //private SessionHandler session;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    TypefaceProvider.registerDefaultIconSets();
    // session = new SessionHandler(getApplicationContext());
    setContentView(R.layout.activity_registration);

    //etUsername = findViewById(R.id.etUsername);
    etPassword = findViewById(R.id.etPassword);
    etConfirmPassword = findViewById(R.id.etConfirmPassword);
    etUsername = findViewById(R.id.etUsername);
    etEmail = findViewById(R.id.etEmail);

    BootstrapButton login = findViewById(R.id.btnRegisterLogin);
    BootstrapButton register = findViewById(R.id.btnRegister);

    //Launch Login screen when Login Button is clicked
    login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
      }
    });

    register.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Retrieve the data entered in the edit texts
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        if (validateInputs()) {
          registerUser();
        }

      }
    });

  }

  /**
   * Display Progress bar while registering
   */
  private void displayLoader() {
    pDialog = new ProgressDialog(RegisterActivity.this);
    pDialog.setMessage("Signing Up.. Please wait...");
    pDialog.setIndeterminate(false);
    pDialog.setCancelable(false);
    pDialog.show();

  }

  /**
   * Launch Dashboard Activity on Successful Sign Up
   */


  private void registerUser() {
    displayLoader();
    JSONObject request = new JSONObject();
    try {
      //Populate the request parameters
      //request.put(KEY_USERNAME, username);
      request.put("username", username);
      request.put(KEY_EMAIL, email);
      request.put(KEY_PASSWORD, password);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    JsonObjectRequest jsArrayRequest = new JsonObjectRequest
            (Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {
                pDialog.dismiss();
                try {
                  System.out.println();
                  //Check if user got registered successfully
                  if (response.getInt(KEY_STATUS) == 0) {
                    //Set the user session
                    //session.loginUser(username,fullName);
                    Toast.makeText(getApplicationContext(),
                            "Registration Successful", Toast.LENGTH_SHORT).show();

                  }else if(response.getInt(KEY_STATUS) == 2){
                    etUsername.setError("Username already taken!");
                    etUsername.requestFocus();
                    Toast.makeText(getApplicationContext(),
                            response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            }, new Response.ErrorListener() {

              @Override
              public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();

                //Display error message whenever an error occurs
                Toast.makeText(getApplicationContext(),
                        "Email Already Exists", Toast.LENGTH_SHORT).show();

              }
            });

    // Access the RequestQueue through your singleton class.
     MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
  }

  /**
   * Validates inputs and shows error if any
   * @return
   */
  protected boolean validateInputs() {
    if (KEY_EMPTY.equals(fullName)) {
      etFullName.setError("Full Name cannot be empty");
      etFullName.requestFocus();
      return false;

    }

    if (KEY_EMPTY.equals(email)) {
      etEmail.setError("Email cannot be empty");
      etEmail.requestFocus();
      return false;
    }

    if (KEY_EMPTY.equals(password)) {
      etPassword.setError("Password cannot be empty");
      etPassword.requestFocus();
      return false;
    }

    if (KEY_EMPTY.equals(confirmPassword)) {
      etConfirmPassword.setError("Confirm Password cannot be empty");
      etConfirmPassword.requestFocus();
      return false;
    }
    if (!password.equals(confirmPassword)) {
      etConfirmPassword.setError("Password and Confirm Password does not match");
      etConfirmPassword.requestFocus();
      return false;
    }

    return true;
  }
}