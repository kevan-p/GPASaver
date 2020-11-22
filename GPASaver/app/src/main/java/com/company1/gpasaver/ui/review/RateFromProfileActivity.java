package com.company1.gpasaver.ui.review;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.VolleyCallBack;

public class RateFromProfileActivity extends AppCompatActivity
{
    public String rating = "http://coms-510-04.cs.iastate.edu:80/rating.php";
    private String server_response;
    private User tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_tutor);
        tutor = (User) getIntent().getSerializableExtra("serialize_tutor");

        TextView mRateTitle = (TextView) findViewById(R.id.tutor_rateTitle);
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.tutor_ratingBar);
        TextView mRatingScale = (TextView) findViewById(R.id.tutor_ratingScale);
        EditText mFeedback = (EditText) findViewById(R.id.tutor_editFeedback);
        Button mSendFeedback = (Button) findViewById(R.id.tutor_btnSubmit);

        if(tutor != null)
            mRateTitle.setText("Rate Tutor - " + tutor.getFirstName());

        //rating scale changes with rating bar changing
        mRatingBar.setOnRatingBarChangeListener((ratingBar, v, b) ->
        {
            mRatingScale.setText(String.valueOf(v));
            switch ((int) ratingBar.getRating())
            {
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
        });

        //send feedback
        mSendFeedback.setOnClickListener(view ->
        {
            if (mFeedback.getText().toString().isEmpty()) { //EditText is empty
                Toast.makeText(RateFromProfileActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
            } else
            {
                int id = tutor.getId();     // get tutor id
                float review = mRatingBar.getRating();      // get rating
                String feedBack = mFeedback.getText().toString();       // get feedback
                sendReview(id, review, feedBack, ()->sendRespond());    // send out
            }
        });
    }

    // invoked upon callback succeed
    private void sendRespond()
    {
        Toast.makeText(RateFromProfileActivity.this, server_response, Toast.LENGTH_LONG).show();
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
