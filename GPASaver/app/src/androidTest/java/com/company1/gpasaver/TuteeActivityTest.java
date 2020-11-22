package com.company1.gpasaver;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
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
public class TuteeActivityTest {

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
    public void messageTest() {
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
                        5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        DataInteraction relativeLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.tutee_list),
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
                .inAdapterView(allOf(withId(R.id.tutee_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(1);
        relativeLayout2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton2.perform(scrollTo(), click());

        DataInteraction relativeLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.tutee_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(2);
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
                .inAdapterView(allOf(withId(R.id.tutee_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(3);
        relativeLayout4.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button2), withText("No"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton4.perform(scrollTo(), click());
    }

    @Test
    public void nameTest() {
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
                        5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tutee_model_from_user), withText("Jeff Green"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("Jeff Green")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tutee_model_from_user), withText("Lebron James"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        1),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("Lebron James")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.tutee_model_from_user), withText("Jane Doe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        2),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("Jane Doe")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tutee_model_from_user), withText("John Smith"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        3),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("John Smith")));
    }

    @Test
    public void courseTest() {
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
                        5),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tutee_model_prompt), withText("COMS410"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("COMS410")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tutee_model_prompt), withText("COMS410"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        1),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("COMS410")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.tutee_model_prompt), withText("COMS410"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        2),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("COMS410")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tutee_model_prompt), withText("COMS410"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tutee_list),
                                        3),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("COMS410")));
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

