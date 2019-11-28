package com.example.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.Model.Comments;
import com.example.android.Model.News;
import com.example.android.R;
import org.w3c.dom.Text;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/17 17:17
 */
public class PingLunAdapter extends ArrayAdapter<Comments> {

    private int resourceId;

    public PingLunAdapter(Context context, int resource, List<Comments> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comments comments = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.comments_image);
        TextView comments_name = (TextView) view.findViewById(R.id.comments_name);
        TextView comments_text = (TextView) view.findViewById(R.id.comments_text);
        TextView comments_time = (TextView) view.findViewById(R.id.comments_time);

       // imageView.setImageResource(comments.getImageId());
        comments_name.setText(comments.getName());
        comments_text.setText(comments.getText());
        comments_time.setText(comments.getTime());

        return view;
    }
}
