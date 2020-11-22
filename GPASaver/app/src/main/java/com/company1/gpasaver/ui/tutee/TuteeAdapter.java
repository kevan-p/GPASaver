package com.company1.gpasaver.ui.tutee;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company1.gpasaver.R;
import com.company1.gpasaver.models.TuteeModel;
import java.util.List;

public class TuteeAdapter extends ArrayAdapter<TuteeModel> {
    static class TuteeViewHolder {
        ImageView image;
        TextView prompt;
        TextView user;
    }

    Context context;
    int resource;
    List<TuteeModel> tutees;

    public TuteeAdapter(@NonNull Context context, int resource, @NonNull List<TuteeModel> Tutee) {
        super(context, resource, Tutee);
        this.context = context;
        this.resource = resource;
        this.tutees = Tutee;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        com.company1.gpasaver.ui.tutee.TuteeAdapter.TuteeViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new com.company1.gpasaver.ui.tutee.TuteeAdapter.TuteeViewHolder();
            viewHolder.image = convertView.findViewById(R.id.tutee_model_image);
            viewHolder.prompt = convertView.findViewById(R.id.tutee_model_prompt);
            viewHolder.user = convertView.findViewById(R.id.tutee_model_from_user);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (com.company1.gpasaver.ui.tutee.TuteeAdapter.TuteeViewHolder) convertView.getTag();
        }

        TuteeModel tutee = tutees.get(position);
        viewHolder.user.setText(tutee.getTutee().getName());
        viewHolder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.boy, null));

        return convertView;
    }



}