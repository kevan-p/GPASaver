package com.company1.gpasaver.ui.home;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.networking.ApiService;
import com.company1.gpasaver.networking.RetrofitClient;
import com.company1.gpasaver.networking.UserResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class HomeViewModel extends Observable {

  // DataBinding fields on the homeFragment
  public ObservableInt peopleProgress;
  public ObservableInt peopleRecycler;
  public ObservableInt peopleLabel;
  public ObservableField<String> messageLabel;

  private List<User> userList;
  private Context context;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();


  public HomeViewModel(@NonNull Context context) {
    this.context = context;
    this.userList = new ArrayList<>();
    peopleProgress = new ObservableInt(View.GONE);
    peopleRecycler = new ObservableInt(View.GONE);
    peopleLabel = new ObservableInt(View.VISIBLE);
    //messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
  }

  public void onClickFabLoad(View view) {
    initializeViews();
    fetchUserList();
  }

  private void fetchUserList() {
    ApiService service = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    Disposable disposable = service.fetchUsers(RetrofitClient.RANDOM_USER_URL)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<UserResponse>() {
          @Override public void accept(UserResponse userResponse) {
            updateUsers(userResponse.getUserList());
            peopleProgress.set(View.GONE);
            peopleLabel.set(View.GONE);
            peopleRecycler.set(View.VISIBLE);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) {
            //messageLabel.set(context.getString(R.string.error_loading_people));
            peopleProgress.set(View.GONE);
            peopleLabel.set(View.VISIBLE);
            peopleRecycler.set(View.GONE);
            throwable.printStackTrace();
          }
        });
    compositeDisposable.add(disposable);
  }

  public void initializeViews() {
    peopleLabel.set(View.GONE);
    peopleRecycler.set(View.GONE);
    peopleProgress.set(View.VISIBLE);
  }

  private void updateUsers(List<User> users) {
    userList.addAll(users);
    setChanged();
    notifyObservers();
  }

  public List<User> getUserList() {
    return userList;
  }

  private void unSubscribeFromObservable() {
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public void reset() {
    unSubscribeFromObservable();
    compositeDisposable = null;
    context = null;
  }

}