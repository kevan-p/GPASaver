package com.company1.gpasaver;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

  @Rule
  public ActivityTestRule<LoginActivity> RAT = new ActivityTestRule<LoginActivity>(LoginActivity.class);
  
  @Test
  public void check_username(){
    onView(withId(R.id.etUsername)).perform(typeText("user00001"));
    onView(withId(R.id.etUsername))
            .check(matches(withText("user00001")));


  }
 @Test
  public void check_password() {
    onView(withId(R.id.etLoginPassword)).perform(typeText("user00001"));
    onView(withId(R.id.etLoginPassword))
            .check(matches(withText("user00001")));
  }

    @Test
    public void main_page() {
        onView(withId(R.id.btnMainApp)).perform(click());
    }
    @Test
    public void register() {
        onView(withId(R.id.btnLoginRegister)).perform(click());
    }
    @Test
    public void login() {
        onView(withId(R.id.btnLogin)).perform(click());
    }




}
