package com.company1.gpasaver;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

  @Rule
  public ActivityTestRule<RegisterActivity> RAT = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

  @Test
  public void check_username(){
    onView(withId(R.id.etUsername)).perform(typeText("Steve"));
    onView(withId(R.id.etUsername))
            .check(matches(withText("Steve")));


  }
 @Test
  public void check_password() {
    onView(withId(R.id.etPassword)).perform(typeText("Steve"));
    onView(withId(R.id.etPassword))
            .check(matches(withText("Steve")));
  }

  @Test
  public void check_confirm_pass() {
    onView(withId(R.id.etConfirmPassword)).perform(typeText("Steve"));
    onView(withId(R.id.etConfirmPassword))
            .check(matches(withText("Steve")));
  }

  @Test
  public void check_email() {
    onView(withId(R.id.etEmail)).perform(typeText("Steve"));
    onView(withId(R.id.etEmail))
            .check(matches(withText("Steve")));
  }

}
