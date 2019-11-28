package com.example.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.android.Model.Comments;
import com.example.android.Model.Music;
import com.example.android.R;

import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/19 10:56
 */
//音乐适配器
public class MusicAdapter extends ArrayAdapter<Music> {

    private int resourceId;

    public MusicAdapter(Context context, int resource, List<Music> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Music music = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView m_name = (TextView) view.findViewById(R.id.music_name);
        TextView m_information = (TextView) view.findViewById(R.id.information);

        m_name.setText(music.getName());
        m_information.setText(music.getInformation());

        return view;
    }
}
