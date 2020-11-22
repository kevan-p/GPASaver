package com.company1.gpasaver.ui.reply;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.review.TutorReviewsFragment;

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        TextView mReviewMe = (TextView) findViewById(R.id.reviewMe);
        EditText mReplyReview = (EditText) findViewById(R.id.replyReview);
        Button mSendReply = (Button) findViewById(R.id.btnReply);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mReviewMe.setText(bundle.getString("review"));
        }

        mSendReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mReplyReview.getText().toString().isEmpty()) { //EditText is empty
                    Toast.makeText(com.company1.gpasaver.ui.reply.ReplyActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    mReplyReview.setText("");
                    Toast.makeText(com.company1.gpasaver.ui.reply.ReplyActivity.this, "Thank you for replying", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}