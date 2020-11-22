package com.company1.gpasaver.ui.myClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company1.gpasaver.R;
import com.company1.gpasaver.models.Availability;
import com.company1.gpasaver.models.Course;

import java.util.ArrayList;

public class ClassListAdapter extends ArrayAdapter<Course>
{

    Context context;
    int resource;

    public ClassListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Course> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String courseId = getItem(position).getId();
        String courseName = getItem(position).getName();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView cId = (TextView) convertView.findViewById(R.id.class_id);
        TextView cName = (TextView) convertView.findViewById(R.id.class_name);

        cId.setText(courseId);
        cName.setText(courseName);

        return convertView;
    }
}
