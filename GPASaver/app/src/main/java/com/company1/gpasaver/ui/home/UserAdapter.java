package com.company1.gpasaver.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.company1.gpasaver.R;
import com.company1.gpasaver.databinding.ItemUsersBinding;
import com.company1.gpasaver.models.User;
import java.util.Collections;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

  private List<User> users;

  public UserAdapter() {
    this.users = Collections.emptyList();
  }

  @NonNull
  @Override public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemUsersBinding itemUsersBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_users, parent, false);
    return new UserAdapterViewHolder(itemUsersBinding);
  }

  @Override public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
    holder.bindUsers(users.get(position));
  }

  @Override public int getItemCount() {
    return users.size();
  }

  public void setPeopleList(List<User> users) {
    this.users = users;
    notifyDataSetChanged();
  }

  static class UserAdapterViewHolder extends RecyclerView.ViewHolder {
    ItemUsersBinding mItemPeopleBinding;

    UserAdapterViewHolder(ItemUsersBinding itemUserBinding) {
      super(itemUserBinding.itemPeople);
      this.mItemPeopleBinding = itemUserBinding;
    }

    void bindUsers(User user) {
      if (mItemPeopleBinding.getUserViewModel() == null) {
        mItemPeopleBinding.setUserViewModel(new UserViewModel(user, itemView.getContext()));
      } else {
        mItemPeopleBinding.getUserViewModel().setPeople(user);
      }
    }
  }
}
