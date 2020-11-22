package com.company1.gpasaver.ui.review;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.company1.gpasaver.MainActivity;
import com.company1.gpasaver.MySingleton;
import com.company1.gpasaver.R;
import com.company1.gpasaver.ui.VolleyCallBack;
import com.company1.gpasaver.ui.myClasses.ListTutorActivity;
import com.company1.gpasaver.ui.reply.ReplyActivity;
import com.company1.gpasaver.ui.reply.ReplyFragment;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TutorReviewsFragment extends Fragment {
   // public ArrayList<Reviews> arrayOfReviews = new ArrayList<Reviews>();
   // reviewAdapter rr = null;
    //String[] reviews = {};//{"no feedback","bad feedback","good feedback"};
    public String getReviews = "http://coms-510-04.cs.iastate.edu:80/Get_tutor_reviews.php?tutorid=";
    List<String> reviews = new ArrayList<String>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tutorreviews,container,false);

        ListView reviewList = (ListView) view.findViewById(R.id.Tutorreviews_list);
        populateReviewList(() ->
        {
            ArrayAdapter<String> reviewAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,reviews);
            reviewList.setAdapter(reviewAdapter);
        });

        reviewList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), reviews.get(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ReplyActivity.class);
                intent.putExtra("review", reviews.get(position));
                startActivity(intent);
            }
        });
            return view;
    }
    private void populateReviewList(final VolleyCallBack callBack)
    {
        String getReviewsURL = getReviews + MainActivity.Mysig.mainUser.getId();;
        JsonArrayRequest getRequest = new JsonArrayRequest

                (Request.Method.GET, getReviewsURL, null, response ->
                {
                    for(int i=0; i<response.length(); i++)
                    {
                        try {
                            JSONObject obj = (JSONObject) response.get(i);
                            //System.out.println(obj);
                            String review = obj.get("feedback").toString();
                            //System.out.println(review);
                            reviews.add(review);
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                    callBack.onSuccess();
                }, error -> error.printStackTrace());

        MySingleton.getInstance(getContext()).addToRequestQueue(getRequest);
    }
}
