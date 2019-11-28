package com.example.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.android.Model.Music;
import com.example.android.Model.Video;
import com.example.android.R;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/20 21:33
 */
public class VideoAdapter extends ArrayAdapter<Video>  {

    private int resourceId;

    public VideoAdapter(Context context, int resource, List<Video> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Video video = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView v_text = (TextView) view.findViewById(R.id.v_text);
        v_text.setText(video.getInfo());

        return view;
    }


}
