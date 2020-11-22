package com.company1.gpasaver;

import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {
    LoginActivity r;
    @Before
    public void init() {
        r = new LoginActivity();
        //     r.onCreate();
        r.etUsername =Mockito.mock(EditText.class);
        r.etPassword =Mockito.mock(EditText .class);
    }


    @Test
    public void no_inputs(){
        r.username = "";
        r.password = "";
        assertEquals(r.validateInputs(), false);
    }
    @Test
    public void no_name(){
        r.username = "";
        r.password = "password";
        assertEquals(r.validateInputs(), false);
    }

    @Test
    public void no_pass(){
        r.username = "user";
        r.password = "";
        assertEquals(r.validateInputs(), false);
    }

    @Test
    public void correct_inputs(){
        r.username = "user";
        r.password = "user";
        assertEquals(r.validateInputs(), true);
    }

}