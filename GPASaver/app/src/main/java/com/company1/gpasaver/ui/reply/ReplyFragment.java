package com.company1.gpasaver.ui.reply;

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
import com.company1.gpasaver.ui.review.ReviewViewModel;

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

public class ReplyFragment extends Fragment{
    private ReplyViewModel replyViewModel;

    TextView mReviewMe;
    EditText mReplyReview;
    Button mSendReply;
    String reviewFeedback;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        replyViewModel = ViewModelProviders.of(this).get(ReplyViewModel.class);
        View root = inflater.inflate(R.layout.activity_reply, container, false);

        mReviewMe = root.findViewById(R.id.reviewMe);
        mReplyReview = root.findViewById(R.id.replyReview);
        mSendReply = root.findViewById(R.id.btnReply);

        //send feedback
        mSendReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mReplyReview.getText().toString().isEmpty()) { //EditText is empty
                    Toast.makeText(getActivity(), "Please fill in reply text box", Toast.LENGTH_LONG).show();
                } else {
                    getData();
                    insertData();
                    mReplyReview.setText("");
                    Toast.makeText(getActivity(), "Thank you for sharing your reply to review", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    public void getData(){
        reviewFeedback = mReplyReview.getText().toString();
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
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String databaseLink = "http://coms-510-04.cs.iastate.edu/rating.php?" +
                            "tutorid=" + 1+
                            "&description=" + reviewFeedback;

                    URL url = new URL(databaseLink);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());


                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

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