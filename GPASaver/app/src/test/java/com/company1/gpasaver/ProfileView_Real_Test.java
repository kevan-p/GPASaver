package com.company1.gpasaver;

import android.content.Context;
import android.content.res.Resources;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.ui.myClasses.ClassListAdapter;
import com.company1.gpasaver.ui.profile.ProfileView_Real;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

//@RunWith(AndroidJUnit4.class)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml")

public class ProfileView_Real_Test {

    ProfileView_Real mClass;

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    public ProfileView_Real_Test() {
        super();
//        System.out.println(appContext.getResources().getStringArray(R.array.tutors)[0]);
        mClass = new ProfileView_Real();
    }

    @Test
    public void findTutorByNameTest() {
        String name = "Leon Olivier";
        int expected = 1;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest1() {
        String name = "Willie Hart";
        int expected = 2;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest2() {
        String name = "Ismail Corneliussen";
        int expected = 3;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest3() {
        String name = "Sander Hansen";
        int expected = 4;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest4() {
        String name = "John Vis";
        int expected = 5;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest5() {
        String name = "David Cooper";
        int expected = 6;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest6() {
        String name = "Edger Thomas";
        int expected = 7;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest7() {
        String name = "Adem King";
        int expected = 8;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

    @Test
    public void findTutorByNameTest8() {
        String name = "Luke Smith";
        int expected = 9;
        int getId = mClass.findTutorByName(name, appContext.getResources().getStringArray(R.array.tutors));
        assertEquals(expected, getId);
    }

}
