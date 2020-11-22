package com.company1.gpasaver.ui.UserView;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.company1.gpasaver.R;
import com.company1.gpasaver.models.User;

import java.util.List;

public class UserListViewAdapter extends ArrayAdapter<User> {

    Context context;
    int resource;
    List<User> users;

    public UserListViewAdapter(@NonNull Context context, int resource, @NonNull List<User> users) {
        super(context, resource, users);
        this.context = context;
        this.resource = resource;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, parent, false);;

        TextView userName = view.findViewById(R.id.label_name);
        TextView userPhone = view.findViewById(R.id.label_phone);
        TextView userEmail = view.findViewById(R.id.label_mail);
        ImageView userImage = view.findViewById(R.id.image_people);
        User user = users.get(position);

        TextView GPA = view.findViewById(R.id.gpa_val);
        TextView Rate = view.findViewById(R.id.rate);
        TextView Class = view.findViewById(R.id.class_tut);

        Glide.with(getContext()).load(user.getPicture()).into(userImage);
        if (user.getGPA() == null || user.getGPA() == "null" ) {
            GPA.setText("student");
            Rate.setText("student");
            Class.setText("student");

            userName.setText(user.getFirstName());
        }
        else {
            GPA.setText("User Rating:" + user.getGPA());
            Rate.setText("Cost Per Hour: " + user.getRate());
            Class.setText(user.getSubjectWillTutor());

            userName.setText(user.getFirstName());
            userEmail.setText(user.getSubjectWillTutor());

        }
        userPhone.setText(user.getPhoneNumber());
        userEmail.setText(user.email);

        //image.setImageDrawable(context.getResources().getDrawable(tutor.getImage(), null));

        return view;
    }

}

