package com.company1.gpasaver.ui.review;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.ui.VolleyCallBack;

public class ReviewActivity extends AppCompatActivity
{
    public String rating = "http://coms-510-04.cs.iastate.edu:80/rating.php";
    private String server_response;

    EditText mTutorId = (EditText) findViewById(R.id.editTutorID);
    RatingBar mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
    TextView mRatingScale = (TextView) findViewById(R.id.textReply);
    EditText mFeedback = (EditText) findViewById(R.id.editFeedback);
    Button mSendFeedback = (Button) findViewById(R.id.btnSubmit);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //rating scale changes with rating bar changing
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b){
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Awful");
                        break;
                    case 2:
                        mRatingScale.setText("Poor");
                        break;
                    case 3:
                        mRatingScale.setText("Average");
                        break;
                    case 4:
                        mRatingScale.setText("Good");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        //send feedback
        mSendFeedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mFeedback.getText().toString().isEmpty()) { //EditText is empty
                    Toast.makeText(ReviewActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                }
                else if(mTutorId.getText().toString().isEmpty()){
                    Toast.makeText(ReviewActivity.this, "Please fill in tutor id", Toast.LENGTH_LONG).show();
                }
                else
                {
                    int id = Integer.valueOf(mTutorId.getText().toString());     // get tutor id
                    float review = mRatingBar.getRating();      // get rating
                    String feedBack = mFeedback.getText().toString();       // get feedback
                    sendReview(id, review, feedBack, ()->sendRespond());    // send out
                }
            }
        });
    }

//Figure out why this is crashing - need it for testing and quality of life purposes.
//    public void minimizeKeyboard(View view) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
//    }

    // invoked upon callback succeed
    private void sendRespond()
    {
        Toast.makeText(ReviewActivity.this, server_response, Toast.LENGTH_LONG).show();
        if(server_response.contains("Successful"))
            finish(); // close this activity if successful
    }

    private void sendReview(int tutorId, float review, String feedback, final VolleyCallBack callBack)
    {
        String URL = rating;
        URL += "?tutorid=" + tutorId;
        URL += "&rating=" + review;
        URL += "&feedback=" + feedback;

        StringRequest postRequest = new StringRequest
                (Request.Method.POST, URL, response ->
                {
                    if(response.equals("Successful"))
                        server_response = "Successful, thank you for your feedback!";
                    else
                        server_response = "unsuccessful, please try again!";

                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
}
