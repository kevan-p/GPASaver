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
import com.company1.gpasaver.models.Tutor;

import java.util.ArrayList;


public class TutorListAdapter extends ArrayAdapter<Tutor> {

    Context context;
    int resource;

    public TutorListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Tutor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        String name = getItem(position).getName();
        TextView nameText = (TextView) convertView.findViewById(R.id.tutor_full_name);
        nameText.setText(name);
        return convertView;
    }


}
