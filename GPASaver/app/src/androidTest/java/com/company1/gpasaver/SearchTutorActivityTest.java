package com.company1.gpasaver;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchTutorActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginTest(){
        onView(withId(R.id.etUsername)).perform(typeText("user00001"));
        onView(withId(R.id.etLoginPassword)).perform(typeText("user00001"));
        //hide keyboard with this click so we can click the button
        onView(withId(R.id.imageView1)).perform(click());
        onView(withId(R.id.btnLogin)).perform(click());
    }

    @Test
    public void searchTutorActivityTest1() {
        loginTest();

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        7),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction bootstrapButton2 = onView(
                allOf(withId(R.id.btnRefresh), withText("Refresh"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        0),
                                3),
                        isDisplayed()));
        bootstrapButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.label_name), withText("Leon Olivier"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Leon Olivier")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.label_phone), withText("0756633638"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                0)),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("0756633638")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.class_tut), withText("Object-oriented Programming"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                0)),
                                4),
                        isDisplayed()));
        textView3.check(matches(withText("Object-oriented Programming")));

//        ViewInteraction textView4 = onView(
//                allOf(withId(R.id.gpa_val), withText("User Rating:4"),
//                        childAtPosition(
//                                allOf(withId(R.id.item_people),
//                                        childAtPosition(
//                                                withId(R.id.listview),
//                                                0)),
//                                6),
//                        isDisplayed()));
//        textView4.check(matches(withText("User Rating:4")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.rate), withText("Cost Per Hour: 4"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                0)),
                                5),
                        isDisplayed()));
        textView5.check(matches(withText("Cost Per Hour: 4")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.label_name), withText("Leon Olivier"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                1)),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Leon Olivier")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.label_phone), withText("0756633638"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                1)),
                                2),
                        isDisplayed()));
        textView7.check(matches(withText("0756633638")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.class_tut), withText("Data Structure"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                1)),
                                4),
                        isDisplayed()));
        textView8.check(matches(withText("Data Structure")));

//        ViewInteraction textView9 = onView(
//                allOf(withId(R.id.gpa_val), withText("User Rating:4"),
//                        childAtPosition(
//                                allOf(withId(R.id.item_people),
//                                        childAtPosition(
//                                                withId(R.id.listview),
//                                                1)),
//                                6),
//                        isDisplayed()));
//        textView9.check(matches(withText("User Rating:4")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.rate), withText("Cost Per Hour: 5"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                1)),
                                5),
                        isDisplayed()));
        textView10.check(matches(withText("Cost Per Hour: 5")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.label_name), withText("Leon Olivier"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                2)),
                                1),
                        isDisplayed()));
        textView11.check(matches(withText("Leon Olivier")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.label_phone), withText("0756633638"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                2)),
                                2),
                        isDisplayed()));
        textView12.check(matches(withText("0756633638")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.class_tut), withText("C for Programmers"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                2)),
                                4),
                        isDisplayed()));
        textView13.check(matches(withText("C for Programmers")));

//        ViewInteraction textView14 = onView(
//                allOf(withId(R.id.gpa_val), withText("User Rating:4"),
//                        childAtPosition(
//                                allOf(withId(R.id.item_people),
//                                        childAtPosition(
//                                                withId(R.id.listview),
//                                                2)),
//                                6),
//                        isDisplayed()));
//        textView14.check(matches(withText("User Rating:4")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.rate), withText("Cost Per Hour: 5"),
                        childAtPosition(
                                allOf(withId(R.id.item_people),
                                        childAtPosition(
                                                withId(R.id.listview),
                                                2)),
                                5),
                        isDisplayed()));
        textView15.check(matches(withText("Cost Per Hour: 5")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
