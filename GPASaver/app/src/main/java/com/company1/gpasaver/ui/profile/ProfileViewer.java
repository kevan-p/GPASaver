package com.company1.gpasaver.ui.profile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.company1.gpasaver.R;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.home.UserViewModel;



public class ProfileViewer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_viewer);
        System.out.println("x");
        User tutor = (User) getIntent ().getSerializableExtra ("serialize_tutor");
        UserViewModel userViewModel = new UserViewModel (tutor, getApplicationContext ());

        TextView name_text = (TextView) findViewById (R.id.show_name);
        name_text.setText (userViewModel.getFullName ());

        TextView mail_text = (TextView) findViewById (R.id.show_mail);
        mail_text.setText (userViewModel.getMail ());

        TextView number_text = (TextView) findViewById (R.id.show_number);
        number_text.setText (userViewModel.getPhoneNumber ());

        TextView userRole = (TextView) findViewById (R.id.userRole);
        userRole.setText ("Tutor");


        int permissionCode = 1;
        String[] permission = {Manifest.permission.SEND_SMS};
        if (ActivityCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permission, permissionCode);
        }

        Button send_SMS = (Button) findViewById (R.id.send_sms);

        send_SMS.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                EditText get_sms = (EditText) findViewById (R.id.sms);
                String sms = get_sms.getText ().toString ();
                Boolean sent_successful = sendSMS(sms, userViewModel.getPhoneNumber ());
                if (sent_successful) {
                    get_sms.getText ().clear ();
                }
            }


        });

    }

    public Boolean sendSMS(String sms, String tutor_number) {
        Boolean sent = false;
        SmsManager sm = SmsManager.getDefault();

        try {
            sm.sendTextMessage(tutor_number,null,sms,null,null);
            Toast.makeText(getApplicationContext(), "SMS Sent Successfully!", Toast.LENGTH_SHORT).show();
            sent = true;
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS Failed to Sent.", Toast.LENGTH_SHORT).show();
        }
        return sent;
    }
}
