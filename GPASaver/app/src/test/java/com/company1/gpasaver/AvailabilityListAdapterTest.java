package com.company1.gpasaver;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.ui.myClasses.ClassListAdapter;
import com.company1.gpasaver.ui.profile.AvailabilityListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.shadows.ShadowInstrumentation.getInstrumentation;

@RunWith(AndroidJUnit4.class)
@Config(manifest = "src/main/AndroidManifest.xml")

public class AvailabilityListAdapterTest {


    private AvailabilityListAdapter mAdapter;

    private Availability ava1;
    private Availability ava2;

    Context appContext = getInstrumentation().getTargetContext();

    public AvailabilityListAdapterTest() {
        super();
        ArrayList<Availability> data = new ArrayList<>();

        ava1 = new Availability(new Course("1", "name1"), "MFW 10AM-11AM");
        ava2 = new Availability(new Course("2", "name2"), "MFW 10AM-12AM");
        data.add(ava1);
        data.add(ava2);
        mAdapter = new AvailabilityListAdapter(appContext, R.layout.item_availability, data);
    }

    @Test
    public void testGetItem() {
        assertEquals(ava1.getCourse().getName(),
                ((Availability) mAdapter.getItem(0)).getCourse().getName());
//                "cName1");
    }
    @Test
    public void testGetItemId() {
        assertEquals(ava1.getCourse().getId(),
                mAdapter.getItem(0).getCourse().getId());
//                mAdapter.getItemId(0));
//            "1");
    }

    @Test
    public void testGetItemAva() {
        assertEquals(ava1.getTime(),
                mAdapter.getItem(0).getTime());
//                mAdapter.getItemId(0));
//            "1");
    }

    @Test
    public void testGetView() {
        View view = mAdapter.getView(0, null, null);

        TextView idText = (TextView) view
                .findViewById(R.id.course_id);

        TextView nameText = (TextView) view
                .findViewById(R.id.course_name);

        TextView timeText = (TextView) view
                .findViewById(R.id.course_time);

        assertNotNull("View is null. ", view);
        assertNotNull("idText TextView is null. ", idText);
        assertNotNull("nameText TextView is null. ", nameText);
        assertNotNull("timeText TextView is null. ", timeText);
    }
}
