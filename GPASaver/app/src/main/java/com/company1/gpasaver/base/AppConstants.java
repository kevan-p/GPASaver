package com.company1.gpasaver.base;

/**
 * Class for storing all application-wide related constants.
 * Mainly for the server URLs and preferences.
 */
public class AppConstants {

  public static final String API_BASE_URL = "http://coms-510-04.cs.iastate.edu";
  public static final String API_GET_REQUESTS = API_BASE_URL + "/get_requests.php";
  public static final String API_LOGIN = API_BASE_URL + "/login";
  public static final String API_PROFILE = API_BASE_URL + "/profile";

  // We can also store session strings here, since
  // PreferenceManager stores them as a key-value pair
  public static String PREF_TOKEN = "pref_token";

}
