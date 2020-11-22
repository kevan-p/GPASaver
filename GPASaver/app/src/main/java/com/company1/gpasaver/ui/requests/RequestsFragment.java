package com.company1.gpasaver.ui.requests;

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
import com.company1.gpasaver.models.TutorRequest;
import com.company1.gpasaver.models.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RequestsFragment extends Fragment {
    private List<TutorRequest> requests;
    private ListView listView;
    private RequestsAdapter adapter;
    private View root;
    private ProgressBar p;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_requests, container, false);
        p = root.findViewById(R.id.requests_progress);
        initList(root);

        return root;
    }

    public void initList(View root) {
        requests = new ArrayList<>();
        new DummyDatabase().execute();

        listView = root.findViewById(R.id.request_list);
        adapter = new RequestsAdapter(requireActivity(), R.layout.request_model, requests);
        listView.setAdapter(adapter);

        StringRequest request = new StringRequest
                (Request.Method.GET, AppConstants.API_GET_REQUESTS, response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        Log.d("JSON_RESPONSE", jsonArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        MySingleton.getInstance(requireActivity()).addToRequestQueue(request);

        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        requests.get(position).accepted = true;
                        requests.remove(position);
                        adapter.notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setMessage("Do you want to accept this tutor request").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        });
    }

    public class DummyDatabase extends AsyncTask<Void, Void, ArrayList<TutorRequest>> {

        ArrayList<TutorRequest> requests = new ArrayList<>();

        @Override
        protected ArrayList<TutorRequest> doInBackground(Void... voids) {
            requests.add(new TutorRequest(new User().setFirstName("Bob").setLastName("Jones"), new User()));
            requests.add(new TutorRequest(new User().setFirstName("Tom").setLastName("Jones"), new User()));
            requests.add(new TutorRequest(new User().setFirstName("Jones").setLastName("Jones"), new User()));
            requests.add(new TutorRequest(new User().setFirstName("Big").setLastName("Jones"), new User()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return requests;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<TutorRequest> requests) {
            super.onPostExecute(requests);
            RequestsFragment.this.requests.addAll(requests);
            RequestsFragment.this.adapter.notifyDataSetChanged();
            p.setVisibility(View.GONE);
        }
    }


}