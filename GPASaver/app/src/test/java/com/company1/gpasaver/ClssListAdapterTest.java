package com.company1.gpasaver;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.ui.myClasses.ClassListAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.shadows.ShadowInstrumentation.getInstrumentation;

@RunWith(AndroidJUnit4.class)
@Config(manifest = "src/main/AndroidManifest.xml")

public class ClssListAdapterTest {


    private ClassListAdapter mAdapter;

    private Course course1;
    private Course course2;

    Context appContext = getInstrumentation().getTargetContext();

    public ClssListAdapterTest() {
        super();
        ArrayList<Course> data = new ArrayList<>();

        course1 = new Course("1", "cName1");
        course2 = new Course("2", "cName2");
        data.add(course1);
        data.add(course2);
        mAdapter = new ClassListAdapter(appContext, R.layout.item_my_class, data);
    }

    @Test
    public void testGetItem() {
        assertEquals("cName1 expected", course1.getName(),
                ((Course) mAdapter.getItem(0)).getName());
//                "cName1");
    }
    @Test
    public void testGetItemId() {
        assertEquals("test getID", course1.getId(),
                mAdapter.getItem(0).getId());
//                mAdapter.getItemId(0));
//            "1");
    }

    @Test
    public void testGetItem1() {
        assertEquals("cName2 expected", course2.getName(),
                ((Course) mAdapter.getItem(1)).getName());
//                "cName1");
    }
    @Test
    public void testGetItemId1() {
        assertEquals("test getID", course2.getId(),
                mAdapter.getItem(1).getId());
//                mAdapter.getItemId(0));
//            "1");
    }

    @Test
    public void testGetView() {
        View view = mAdapter.getView(1, null, null);

        TextView idText = (TextView) view
                .findViewById(R.id.class_id);

        TextView nameText = (TextView) view
                .findViewById(R.id.class_name);

        assertNotNull("View is null. ", view);
        assertNotNull("idText TextView is null. ", idText);
        assertNotNull("nameText TextView is null. ", nameText);
    }
}
