package com.company1.gpasaver.prefs;

/**
 * Interface for easily accessing UserPreferences.
 */
public interface PreferencesHelper {

  String getLoginToken();  // Login token for the server.

  void saveLoginToken(String token);  // Save token


}
