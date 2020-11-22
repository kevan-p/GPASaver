package com.company1.gpasaver;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.Tutor;
import com.company1.gpasaver.ui.myClasses.ClassListAdapter;
import com.company1.gpasaver.ui.myClasses.TutorListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@Config(manifest = "src/main/AndroidManifest.xml")

public class TutorListAdapterTest {


    private TutorListAdapter mAdapter;

    private Tutor tutor1;
    private Tutor tutor2;

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    public TutorListAdapterTest() {
        super();
        ArrayList<Tutor> data = new ArrayList<>();

        tutor1 = new Tutor("tutorName1", "1");
        tutor2 = new Tutor("tutorName2", "2");
        data.add(tutor1);
        data.add(tutor2);
        mAdapter = new TutorListAdapter(appContext, R.layout.item_tutor_list, data);
    }

    @Test
    public void testGetItem() {
        assertEquals("cName1 expected", tutor1.getName(),
                ((Tutor) mAdapter.getItem(0)).getName());
//                "cName1");
    }
    @Test
    public void testGetItemId() {
        assertEquals("test getID", tutor1.getId(),
                mAdapter.getItem(0).getId());
//                mAdapter.getItemId(0));
//            "1");
    }

    @Test
    public void testGetItem1() {
        assertEquals("cName2 expected", tutor2.getName(),
                ((Tutor) mAdapter.getItem(1)).getName());
//                "cName1");
    }
    @Test
    public void testGetItemId1() {
        assertEquals("test getID", tutor2.getId(),
                ((Tutor) mAdapter.getItem(1)).getId());
//                mAdapter.getItemId(0));
//            "1");
    }

    @Test
    public void testGetView() {
        View view = mAdapter.getView(1, null, null);

        TextView nameText = (TextView) view
                .findViewById(R.id.tutor_full_name);

        assertNotNull("View is null. ", view);
        assertNotNull("idText TextView is null. ", nameText);
    }
}
