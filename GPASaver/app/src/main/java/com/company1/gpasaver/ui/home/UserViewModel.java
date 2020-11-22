package com.company1.gpasaver.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.company1.gpasaver.models.User;

public class UserViewModel extends BaseObservable {

  private User user;
  private Context context;

  public UserViewModel(User user, Context context) {
    this.user = user;
    this.context = context;
  }

  public String getFullName() {
    user.fullName =
        user.name.title + "." + user.name.firts + " " + user.name.last;
    return user.fullName;
  }

  public String getPhoneNumber() {
    return user.phoneNumber;
  }

  public String getMail() {
    return user.email;
  }

  public String getPictureProfile() {
    return user.picture.medium;
  }

  @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
    Glide.with(imageView.getContext()).load(url).into(imageView);
  }

  public void onItemClick(View view) {
   // context.startActivity(PeopleDetailActivity.launchDetail(view.getContext(), people));
  }

  public void setPeople(User user) {
    this.user = user;
    notifyChange();
  }

}
