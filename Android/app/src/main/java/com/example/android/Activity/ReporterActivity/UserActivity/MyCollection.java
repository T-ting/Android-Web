package com.example.android.Activity.ReporterActivity.UserActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.android.Activity.ReporterActivity.VideoActivity.VideoShow;
import com.example.android.Adapter.MusicAdapter;
import com.example.android.Adapter.NewsAdapter;
import com.example.android.Adapter.VideoAdapter;
import com.example.android.Model.Music;
import com.example.android.Model.News;
import com.example.android.Model.User;
import com.example.android.Model.VideoList;
import com.example.android.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyCollection extends AppCompatActivity {

    private List<News> newsList = new ArrayList<>();
    private User user;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    NewsAdapter newsAdapter = new NewsAdapter(MyCollection.this,R.layout.index_item,newsList);
                    ListView listView = (ListView) findViewById(R.id.newslist);
                    listView.setAdapter(newsAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news1 = newsList.get(position);
                            Intent intent1 = new Intent(MyCollection.this,ShowNews.class);
                            intent1.putExtra("user",user);
                            intent1.putExtra("news",news1.getText());
                            intent1.putExtra("id",news1.getId());
                            startActivity(intent1);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        user = (User) getIntent().getSerializableExtra("user");

        String url = "http://10.0.2.2:8080/se_SC.action?user_id="+user.getId();
        send(url,handler,1);
    }

    public void send(final String url, final Handler handler,final int i){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String rs = response.body().string();
                    Message message = new Message();
                    if (i == 1) {
                        pare(rs);
                        message.what = 1;
                    }
                    else {
                        message.what = 2;
                    }
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void pare(String d){
        try {
            JSONArray jsonArray = new JSONArray(d);
            for (int i =jsonArray.length()-1; i >= 0; i--) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String text = jsonObject.getString("text");
                String time = jsonObject.getString("time");
                String title = jsonObject.getString("title");
                News news = new News(id,name, text, time, title);
                newsList.add(news);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
