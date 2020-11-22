package com.company1.gpasaver;


import android.view.View;
import android.widget.RatingBar;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.company1.gpasaver.ui.review.RateFromProfileActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class RateFromProfileActivityTest {

  @Rule
  public ActivityTestRule<RateFromProfileActivity> RAT = new ActivityTestRule<RateFromProfileActivity>(RateFromProfileActivity.class);

  @Test
  public void check_feedback(){
    onView(withId(R.id.tutor_editFeedback)).perform(typeText("this is my feedback"));
    onView(withId(R.id.tutor_editFeedback))
            .check(matches(withText("this is my feedback")));
  }

  @Test
  public void check_feedback2(){
    onView(withId(R.id.tutor_editFeedback)).perform(typeText("this is my feedback2"));
    onView(withId(R.id.tutor_editFeedback))
            .check(matches(withText("this is my feedback2")));
  }

  @Test
  public void check_feedback3(){
    onView(withId(R.id.tutor_editFeedback)).perform(typeText("this is my feedback3"));
    onView(withId(R.id.tutor_editFeedback))
            .check(matches(withText("this is my feedback3")));
  }

 @Test
  public void check_rate() {
//    onView(withId(R.id.tutor_ratingBar)).perform(new SetRating().perform());
//    onView(withId(R.id.tutor_rateTitle))
//            .check(matches(withText("Tutor-Steve")));
  }


  public final class SetRating implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
      Matcher <View> isRatingBarConstraint = ViewMatchers.isAssignableFrom(RatingBar.class);
      return isRatingBarConstraint;
    }

    @Override
    public String getDescription() {
      return "Custom view action to set rating.";
    }

    @Override
    public void perform(UiController uiController, View view) {
      RatingBar ratingBar = (RatingBar) view;
      ratingBar.setRating(3);
    }
  }

}
