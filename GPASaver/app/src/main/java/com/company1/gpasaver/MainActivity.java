package com.company1.gpasaver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.company1.gpasaver.models.TutorRequest;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.ui.UserView.UserListViewAdapter;
import com.company1.gpasaver.ui.home.HomeFragment;
import com.company1.gpasaver.ui.profile.ProfileView_Real;
import com.company1.gpasaver.ui.profile.ProfileViewer;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Inflates the Navigation drawer for navigation to fragments.
 * The Nav drawer is meant as a base for inflating all fragments.
 *
 * From Google's docs, they want a single activity inflating different fragments
 * for different functionality. Obviously this could get a bit more complex, so we can change it later if needed.
 */
public class MainActivity extends BaseActivity {
  private AppBarConfiguration mAppBarConfiguration;
  protected ArrayList<User> user =new ArrayList<>();
  protected String users_list = "http://10.0.2.2:8080/usersList";
  public static User main_user;
  public static MySingleton Mysig;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    //FloatingActionButton fab = findViewById(R.id.fab);
    //fab.setOnClickListener(view ->
    //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    //  .setAction("Action", null).show());
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    mAppBarConfiguration = new AppBarConfiguration.Builder(
      R.id.nav_home, R.id.nav_profile, R.id.nav_slideshow,
      R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.nav_requests, R.id.nav_cancel_tutor)
      .setDrawerLayout(drawer)
      .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);

    Mysig.mainUser = (User) getIntent ().getSerializableExtra ("serialize_tutor");
  }

  public static User getUser() {
    return main_user;
  }

  public void setUser(User someVariable) {
    this.main_user = someVariable;
  }

  @Override protected int getActivityLayout() {
    return R.layout.activity_main;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration)
      || super.onSupportNavigateUp();
  }

}
