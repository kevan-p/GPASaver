package com.company1.gpasaver.ui.requests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.company1.gpasaver.R;
import com.company1.gpasaver.models.RealTutorRequestEntity;

import org.w3c.dom.Text;

import java.util.List;

public class MyRequestsAdapter extends ArrayAdapter<RealTutorRequestEntity> {
    static class MyRequestsViewHolder {
        TextView userId;
        TextView tutorId;
        TextView courseId;
        TextView requestTime;
        TextView startTime;
        TextView endTime;
    }

    private Context _context;
    private  int _resource;
    private  List<RealTutorRequestEntity> _requests;

    public MyRequestsAdapter(Context context, int resource, @NonNull List<RealTutorRequestEntity> requests) {
        super(context, resource, requests);

        this._context = context;
        this._resource = resource;
        this._requests = requests;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        MyRequestsAdapter.MyRequestsViewHolder viewHolder;

        if(convertView==null){

            LayoutInflater inflater = LayoutInflater.from(this._context);
            convertView = inflater.inflate(this._resource, parent, false);

            viewHolder = new MyRequestsAdapter.MyRequestsViewHolder();

            viewHolder.userId = convertView.findViewById(R.id.user_id);
            viewHolder.tutorId = convertView.findViewById(R.id.tutor_id);
            viewHolder.courseId = convertView.findViewById(R.id.course_id);
            viewHolder.requestTime = convertView.findViewById(R.id.request_time);
            viewHolder.startTime = convertView.findViewById(R.id.start_time);
            viewHolder.endTime = convertView.findViewById(R.id.end_time);

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (MyRequestsAdapter.MyRequestsViewHolder) convertView.getTag();
        }

        RealTutorRequestEntity request = this._requests.get(position);

        viewHolder.userId.setText("Student: " + request.userFullName);
        viewHolder.tutorId.setText("Tutor: " + request.tutorFullName);
        viewHolder.courseId.setText("Course: " + request.courseName);
        viewHolder.requestTime.setText("Request Time: " + request.requestDate.toLocaleString());
        viewHolder.startTime.setText("Start Time: " + request.startTime.toLocaleString());
        viewHolder.endTime.setText("End Time: " + request.endTime.toLocaleString());

        return convertView;
    }
}
