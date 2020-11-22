package com.company1.gpasaver;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;

/**
 * BaseActivity abstract class where all the convenience methods can go.
 * All activities could extend this class and work perfectly fine.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getActivityLayout());
  }

  // returns the activity layout R.layout.foo;
  // just so we don't have to call it in onCreate()
  protected abstract int getActivityLayout();

  // Sometimes won't work on older Android devices
  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if (imm != null) {
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      }
    }
  }

  // Returns the versionCode from the Module build.gradle.
  // Everyone should update the versionCode from time to time.
  public int getAppVersion() {
    int versionCode = 0;
    try {
      versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return versionCode;
  }

}
