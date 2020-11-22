package com.company1.gpasaver.ui.profile;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.company1.gpasaver.models.User;


/**
 * ViewModels handle all of the business logic of the app.
 * They change textfields and UI elements based on responses from an API
 * or other business logic.
 */
public class ProfileViewModel {

  private User user;

  public ProfileViewModel(User user) {
      this.user = user;
  }

  // Since we don't set the full name in the model, we do this strange conversion...
  public String getFullName() {
      return user.fullName = user.firstName + " " + user.lastName;
  }

  public String getEmail() {
      return user.email;
  }

  public int getEmailVisibility() {
    return user.hasEmail() ? View.VISIBLE : View.GONE;
  }

  public String getPhone() {
      return user.phoneNumber;
  }

  public int getProfilePicture() {
      return user.image;
  }

  @BindingAdapter({"imageUrl"})
  public static void loadImage(ImageView view, String imageUrl) {
    Glide.with(view.getContext()).load(imageUrl).into(view);
  }

}