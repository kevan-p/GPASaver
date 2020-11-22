package com.company1.gpasaver.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.company1.gpasaver.ListViewAdapter;
import com.company1.gpasaver.R;

import com.company1.gpasaver.databinding.FragmentHomeBinding;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.home.RecyclerTouchListener;
import com.company1.gpasaver.ui.profile.ProfileViewer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeFragment extends Fragment implements Observer {
  private List<User> tutors;
  private ListView listView;
  private EditText searchBar;
  private ListViewAdapter adapter;
  private View root;

  private FragmentHomeBinding fragmentHomeBinding;
  private HomeViewModel homeViewModel;

  private String login_url = "http://coms-510-04.cs.iastate.edu:80/login.php";


  public View onCreateView(@NonNull LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {
    root = inflater.inflate(R.layout.fragment_home, container, false);

    homeViewModel = new HomeViewModel(getActivity());

    fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
    View view = fragmentHomeBinding.getRoot();
    fragmentHomeBinding.setMainViewModel(homeViewModel);

    setupUserListView(fragmentHomeBinding.listPeople);
    setupObserver(homeViewModel);

    return view;

  }

  private void setupUserListView(RecyclerView recyclerView) {
    UserAdapter adapter = new UserAdapter();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity (), recyclerView, new RecyclerTouchListener.ClickListener() {
      @Override
      public void onClick(View view, int position) {

        // First get the user
        User tutor = homeViewModel.getUserList().get(position);

        // send to dialog
        Intent startIntent = new Intent(getContext (), ProfileViewer.class);
        startIntent.putExtra ("serialize_tutor", tutor);
        startActivity(startIntent);

      }

      @Override
      public void onLongClick(View view, int position) {
        Toast.makeText(getActivity(), position+ " is selected successfully long", Toast.LENGTH_SHORT).show();
      }
    }));
  }

  private void setupObserver(Observable observable) {
    observable.addObserver(this);
  }


  @Override public void update(Observable observable, Object o) {
    if (observable instanceof HomeViewModel) {
      UserAdapter userAdapter = (UserAdapter) fragmentHomeBinding.listPeople.getAdapter();
      HomeViewModel homeViewModel = (HomeViewModel) observable;
      userAdapter.setPeopleList(homeViewModel.getUserList());
    }
  }
}