package com.company1.gpasaver.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.company1.gpasaver.base.AppConstants;

public class UserPreferences implements PreferencesHelper {
  private SharedPreferences settings;
  private Context mContext;
  private static UserPreferences sInstance;

  public UserPreferences(Context context) {
    this.mContext = context;
    settings = PreferenceManager.getDefaultSharedPreferences(context);
  }

  /**
   * instance of UserPreferences by Singleton pattern.
   */
  public static UserPreferences getInstance(Context mContext) {
    if (sInstance == null) {
      synchronized (UserPreferences.class) {
        if (sInstance == null) {
          sInstance = new UserPreferences(mContext);
        }
      }
    }
    return sInstance;
  }

  @Override public String getLoginToken() {
    return settings.getString(AppConstants.PREF_TOKEN, "");
  }

  @Override public void saveLoginToken(String token) {
    settings.edit().putString(AppConstants.PREF_TOKEN, token).apply();
  }
}
