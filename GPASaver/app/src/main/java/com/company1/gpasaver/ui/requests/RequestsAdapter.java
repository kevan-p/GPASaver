package com.company1.gpasaver.ui.requests;

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
import com.company1.gpasaver.models.TutorRequest;
import java.util.List;


public class RequestsAdapter extends ArrayAdapter<TutorRequest> {
    static class RequestsViewHolder {
        ImageView image;
        TextView prompt;
        TextView user;
    }

    Context context;
    int resource;
    List<TutorRequest> requests;

    public RequestsAdapter(@NonNull Context context, int resource, @NonNull List<TutorRequest> TutorRequests) {
        super(context, resource, TutorRequests);
        this.context = context;
        this.resource = resource;
        this.requests= TutorRequests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            RequestsViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            viewHolder = new RequestsViewHolder();
            viewHolder.image = convertView.findViewById(R.id.request_model_image);
            viewHolder.prompt = convertView.findViewById(R.id.request_model_prompt);
            viewHolder.user = convertView.findViewById(R.id.request_model_from_user);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (RequestsViewHolder) convertView.getTag();
        }

            TutorRequest request = requests.get(position);
            viewHolder.user.setText(request.getFrom().getName());
            //if (request.getFrom().getImage() != -1)
               // viewHolder.image.setImageDrawable(context.getResources().getDrawable(request.getFrom().getImage(), null));
            //else
                viewHolder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.boy, null));

        return convertView;
    }



}
