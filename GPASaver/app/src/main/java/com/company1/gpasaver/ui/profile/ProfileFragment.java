package com.company1.gpasaver.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.company1.gpasaver.R;
import com.company1.gpasaver.networking.ApiService;
import com.company1.gpasaver.networking.RetrofitClient;

public class ProfileFragment extends Fragment {

  private ProfileViewModel profileViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {

    ApiService service = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    ////Call<List<User>> call = service.fetchUsers("1");
    //
    //call.enqueue(new Callback<List<User>>() {
    //  @Override public void onResponse(Call<List<User>> call, Response<List<User>> response) {
    //    //loadDataList(response.body());
    //  }
    //
    //  @Override public void onFailure(Call<List<User>> call, Throwable throwable) {
    //    Toast.makeText(getActivity(), "Unable to load users", Toast.LENGTH_SHORT).show();
    //  }
    //});

    //profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    View root = inflater.inflate(R.layout.fragment_profile, container, false);

    //final TextView textView = root.findViewById(R.id.userRole);

    // Whenever the text is changed in the viewModel, it will update the UI here
    // As long as you call .observe on the element you want to update.

    //profileViewModel.getText().observe(this, s -> textView.setText(s));
    return root;
  }

}