package com.company1.gpasaver.ui.tutee;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.base.AppConstants;
import com.company1.gpasaver.models.TuteeModel;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.tutee.TuteeAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TuteeFragment extends Fragment {
    private List<TuteeModel> tutees;
    private ListView listView;
    private TuteeAdapter adapter;
    private View root;
    private ProgressBar p;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_tutees, container, false);
        p = root.findViewById(R.id.tutee_progress);
        initList(root);

        return root;
    }

    public void initList(View root) {
        tutees = new ArrayList<>();
        new com.company1.gpasaver.ui.tutee.TuteeFragment.DatabaseConnection().execute();

        listView = root.findViewById(R.id.tutee_list);
        adapter = new TuteeAdapter(requireActivity(), R.layout.tutee_model, tutees);
        listView.setAdapter(adapter);

        StringRequest tutee = new StringRequest
                (Request.Method.GET, AppConstants.API_GET_REQUESTS, response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        Log.d("JSON_RESPONSE", jsonArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        MySingleton.getInstance(requireActivity()).addToRequestQueue(tutee);

        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        tutees.get(position).message = true;
                        tutees.remove(position);
                        adapter.notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setMessage("Would you like to message this Tutee?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        });
    }

    public class DatabaseConnection extends AsyncTask<Void, Void, ArrayList<TuteeModel>> {

        ArrayList<TuteeModel> tutee = new ArrayList<>();

        @Override
        protected ArrayList<TuteeModel> doInBackground(Void... voids) {
            tutee.add(new TuteeModel(new User().setFirstName("Jeff").setLastName("Green"), new User()));
            tutee.add(new TuteeModel(new User().setFirstName("Lebron").setLastName("James"), new User()));
            tutee.add(new TuteeModel(new User().setFirstName("Jane").setLastName("Doe"), new User()));
            tutee.add(new TuteeModel(new User().setFirstName("John").setLastName("Smith"), new User()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return tutee;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<TuteeModel> tutees) {
            super.onPostExecute(tutees);
            com.company1.gpasaver.ui.tutee.TuteeFragment.this.tutees.addAll(tutees);
            com.company1.gpasaver.ui.tutee.TuteeFragment.this.adapter.notifyDataSetChanged();
            p.setVisibility(View.GONE);
        }
    }
}