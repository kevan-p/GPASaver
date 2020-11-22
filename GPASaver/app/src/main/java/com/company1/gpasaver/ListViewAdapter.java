package com.company1.gpasaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company1.gpasaver.models.User;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<User> {

    Context context;
    int resource;
    List<User> users;


    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<User> users) {
        super(context, resource, users);
        this.context = context;
        this.resource = resource;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(resource, null);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewSubjects = view.findViewById(R.id.textViewSubjects);
        ImageView image = view.findViewById(R.id.imageView);
        User tutor = users.get(position);

        textViewName.setText(tutor.getFirstName() + " " + tutor.getLastName());
        textViewSubjects.setText(tutor.getSubjectWillTutor());
        image.setImageDrawable(context.getResources().getDrawable(tutor.getImage(), null));

        return view;
    }

}

