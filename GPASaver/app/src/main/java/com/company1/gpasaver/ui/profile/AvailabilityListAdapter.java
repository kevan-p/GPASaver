package com.company1.gpasaver.ui.profile;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;
import com.company1.gpasaver.models.User;
import com.company1.gpasaver.R;

import java.util.ArrayList;

public class AvailabilityListAdapter extends ArrayAdapter<Availability> {

    Context context;
    int resource;

    public AvailabilityListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Availability> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course course = getItem(position).getCourse();
        String time = getItem(position).getTime();

        Availability availability = new Availability(course, time);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView cId = (TextView) convertView.findViewById(R.id.course_id);
        TextView cName = (TextView) convertView.findViewById(R.id.course_name);
        TextView cTime = (TextView) convertView.findViewById(R.id.course_time);

        cId.setText(course.getId());
        cName.setText(course.getName());
        cTime.setText(time);

        return convertView;
    }
}
