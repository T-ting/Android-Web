package com.example.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.android.Activity.ReporterActivity.UserActivity.IndexActivity;
import com.example.android.MainActivity;
import com.example.android.Model.News;
import com.example.android.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢朝康
 * @date 2019/11/16 22:27
 */
public class NewsAdapter  extends ArrayAdapter<News>{

    private int resourceId;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title,name,time;
        title = (TextView) view.findViewById(R.id.news_title);
        name = (TextView) view.findViewById(R.id.news_author);
        time = (TextView) view.findViewById(R.id.news_time);
        title.setText(news.getTitle());
        name.setText(news.getName());
        time.setText(news.getTime());
        return view;
    }
}
