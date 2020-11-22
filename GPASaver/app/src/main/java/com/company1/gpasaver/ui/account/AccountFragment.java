package com.company1.gpasaver.ui.account;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class AccountFragment extends  Fragment{
    private AccountViewModel accountViewModel;

    EditText mAccount;
    Button mSubmit;
    String account;
    double balance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.activity_accountsetup, container, false);

        mAccount = root.findViewById(R.id.editAccount);
        mSubmit = root.findViewById(R.id.btnSubmit);

        //submit account
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAccount.length() != 16) { //EditText is empty
                    Toast.makeText(getActivity(), "Not valid account", Toast.LENGTH_LONG).show();
                } else {
                    getData();
                    insertData();
                    Toast.makeText(getActivity(), "Set up account successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TextView textView = root.findViewById(R.id.textReply);
        accountViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    public void getData(){
        account = mAccount.getText().toString();
        balance = 100.0;
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
                    String databaseLink = "http://coms-510-04.cs.iastate.edu/account.php?" +
                            "userid=" + 1+
                            "&account=" + account+
                            "&balance=" + balance;

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
