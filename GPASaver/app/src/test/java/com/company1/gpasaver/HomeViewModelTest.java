package com.company1.gpasaver;

import android.content.Context;
import android.view.View;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.networking.ApiService;
import com.company1.gpasaver.ui.home.HomeViewModel;
import io.reactivex.Observable;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class) public class HomeViewModelTest {

  private static final String URL_TEST = "http://api.randomuser.me/?results=10&nat=en";

  @Mock ApiService apiService;

  private Context mockContext = mock(Context.class);

  private HomeViewModel homeViewModel;


  @Before public void setUpMainViewModelTest() {
    homeViewModel = new HomeViewModel(mockContext);
  }

  @Test public void getUserListFromApi() {
    List<User> peoples = FakeUserGenerator.getUserList();
    doReturn(Observable.just(peoples)).when(apiService).fetchUsers(URL_TEST);
  }

  @Test public void ensureTheViewsAreInitializedCorrectly() {
    homeViewModel.initializeViews();
    assertEquals(View.GONE, homeViewModel.peopleLabel.get());
    assertEquals(View.GONE, homeViewModel.peopleRecycler.get());
    assertEquals(View.VISIBLE, homeViewModel.peopleProgress.get());
  }

}
