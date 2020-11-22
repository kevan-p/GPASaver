package com.company1.gpasaver;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
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

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RequestsActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void requestsTest() {
        ViewInteraction bootstrapButton = onView(
                allOf(withId(R.id.btnMainApp), withText("Skip Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        bootstrapButton.perform(click());

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
                        4),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.request_model_from_user), withText("Bob Jones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.request_list),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("Bob Jones")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.request_model_from_user), withText("Tom Jones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.request_list),
                                        1),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("Tom Jones")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.request_model_from_user), withText("Jones Jones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.request_list),
                                        2),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("Jones Jones")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.request_model_from_user), withText("Big Jones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.request_list),
                                        3),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("Big Jones")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.request_model_from_user), withText("Big Jones"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.request_list),
                                        3),
                                2),
                        isDisplayed()));
        textView5.check(matches(withText("Big Jones")));

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.request_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        relativeLayout.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton.perform(scrollTo(), click());

        DataInteraction relativeLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.request_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(1);
        relativeLayout2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.request_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(1);
        relativeLayout3.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton3.perform(scrollTo(), click());

        DataInteraction relativeLayout4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.request_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(2);
        relativeLayout4.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());
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
