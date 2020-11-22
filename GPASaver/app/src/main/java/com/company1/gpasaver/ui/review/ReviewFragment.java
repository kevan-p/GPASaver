package com.company1.gpasaver.ui.review;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.company1.gpasaver.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class ReviewFragment extends Fragment{
    private ReviewViewModel reviewViewModel;
    public String rating = "http://coms-510-04.cs.iastate.edu:80/rating.php";

    EditText mTutorID;
    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    int tutorID;
    int ratingstar;
    String feedback;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);
        View root = inflater.inflate(R.layout.activity_review, container, false);

        mTutorID = root.findViewById(R.id.editTutorID);
        mRatingBar = root.findViewById(R.id.ratingBar);
        mRatingScale = root.findViewById(R.id.ratingScale);
        mFeedback = root.findViewById(R.id.editFeedback);
        mSendFeedback = root.findViewById(R.id.btnSubmit);

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
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTutorID.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in tutor id", Toast.LENGTH_LONG).show();
                }
                else if (mFeedback.getText().toString().isEmpty()) { //EditText is empty
                    Toast.makeText(getActivity(), "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    getData();
                    insertData();
                    mTutorID.setText("");
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(getActivity(), "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TextView textView = root.findViewById(R.id.textReply);
        reviewViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    public void getData(){
        tutorID = Integer.parseInt(mTutorID.getText().toString());
        ratingstar = (int) mRatingBar.getRating();
        feedback = mFeedback.getText().toString();

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
//                    String databaseLink = "http://coms-510-04.cs.iastate.edu/rating.php?" +
//                            "tutorid=" + 1+
//                            "&rating=" + ratingstar+
//                            "&description=" + feedback;

                    String databaseLink = rating;
                    databaseLink += "?tutorid=" + tutorID;
                    databaseLink += "&rating=" + ratingstar;
                    databaseLink += "&feedback=" + feedback;

                    URL url = new URL(databaseLink);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                   // wr.write(data);
                    //Swr.flush();

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
}
