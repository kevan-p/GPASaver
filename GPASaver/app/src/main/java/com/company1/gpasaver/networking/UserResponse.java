package com.company1.gpasaver.networking;

import com.company1.gpasaver.models.User;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Response from the randomuser.me API
 */
public class UserResponse {

  @SerializedName("results") private List<User> userList;

  public List<User> getUserList() {
    return userList;
  }
}
