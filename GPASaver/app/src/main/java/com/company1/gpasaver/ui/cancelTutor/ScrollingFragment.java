package com.company1.gpasaver.ui.cancelTutor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.CancelTutor;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.TutorRequest;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.myClasses.ClassListAdapter;
import com.company1.gpasaver.ui.myClasses.ListTutorActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScrollingFragment extends Fragment {

    public String getTutorRequests = "http://coms-510-04.cs.iastate.edu:80/list_tutored_classes.php?id=";
    public String deleteTutorRequest = "http://coms-510-04.cs.iastate.edu:80/delete_tutor_request.php?";
    CancelTutorAdapter adapter;
    private TextView descTxt;
    private ArrayList<CancelTutor> requests;
    private ListView classList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrolling, container, false);
        classList = (ListView) view.findViewById(R.id.tutor_list);
        requests = new ArrayList<>();

        populateTutoredList(() ->
        {
            adapter = new CancelTutorAdapter(getContext(), R.layout.item_my_class, requests);
            descTxt = (TextView) view.findViewById(R.id.tutor_desc_txt);
            descTxt.setText("You are being tutored in " + requests.size() + " classes");
            classList.setAdapter(adapter);
            classList.setOnItemClickListener((adapterView, view1, i, l) ->
            {
                new AlertDialog.Builder(getContext())
                        .setTitle("Withdraw from tutoring?")
                        .setMessage("Are you sure you want to cancel your tutoring for this class?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                CancelTutor classClicked = requests.get(i);
                                //delete remotely
                                deleteTutoring(classClicked, () -> {});
                                //delete locally
                                adapter.remove(classClicked);
                                adapter.notifyDataSetChanged();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            });
        });

        return view;
    }

    private void populateTutoredList(final VolleyCallBack callBack) {
        int id = MainActivity.Mysig.mainUser.getId();
        String request = getTutorRequests + id;
        JsonArrayRequest getRequest = new JsonArrayRequest
                (Request.Method.GET, request, null, response ->
                {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = (JSONObject) response.get(i);
                            String courseName = obj.get("course").toString();
                            String courseId = obj.get("courseId").toString();
                            String tutorName = obj.get("username").toString();
                            int tutorId = Integer.parseInt(obj.get("tutorId").toString());
                            requests.add(new CancelTutor(new User(tutorId, tutorName), new Course(courseId, courseName)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(getContext()).addToRequestQueue(getRequest);
    }

    private void deleteTutoring(CancelTutor selected, final VolleyCallBack callBack) {
        String r = deleteTutorRequest + "id=" + MainActivity.Mysig.mainUser.getId()
                + "&course=" + selected.getCourse().getId() + "&tutor=" + selected.getTutor().getId();
        JsonArrayRequest deleteRequest = new JsonArrayRequest
                (Request.Method.DELETE, r, null, null, null);

        MySingleton.getInstance(getContext()).addToRequestQueue(deleteRequest);
    }
}