package com.example.android.Activity.ReporterActivity.UserActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.android.Activity.ReporterActivity.VideoActivity.VideoShow;
import com.example.android.Adapter.MusicAdapter;
import com.example.android.Adapter.NewsAdapter;
import com.example.android.Adapter.VideoAdapter;
import com.example.android.Model.*;
import com.example.android.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class sousuo extends AppCompatActivity {

    private EditText editText;
    private Button news,music,video;
    private String text;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private List<News> newsList = new ArrayList<>();
    private List<Music> musicList = new ArrayList<>();
    private List<Video> videoList  = new ArrayList<>();

    private User user;

    private int i;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    NewsAdapter newsAdapter = new NewsAdapter(sousuo.this,R.layout.index_item,newsList);
                    ListView newsview = (ListView) findViewById(R.id.show_result);
                    newsview.setAdapter(newsAdapter);
                    newsview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news = newsList.get(position);
                            String j;

                            Intent intent = new Intent(sousuo.this,ShowNews.class);
                            intent.putExtra("user",user);

                            intent.putExtra("news",news.getText());
                            intent.putExtra("id",news.getId());
                            startActivity(intent);
                        }
                    });
                    break;
                case 2:
                    MusicAdapter musicAdapter = new MusicAdapter(sousuo.this,R.layout.music_item,musicList);
                    ListView musicview = (ListView) findViewById(R.id.show_result);
                    musicview.setAdapter(musicAdapter);
                    musicview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Music music = musicList.get(position);

                            if (ContextCompat.checkSelfPermission(sousuo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(sousuo.this,new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },1);
                            }else {
                                mediaPlayer.reset();
                                initmusic(music.getPath());
                            }

                            if (!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                            }
                            else {
                                mediaPlayer.reset();
                                initmusic(music.getPath());
                            }
                        }
                    });

                    break;
                case 3:
                    VideoAdapter videoAdapter = new VideoAdapter(sousuo.this,R.layout.video_item,videoList);
                    ListView videoview = (ListView) findViewById(R.id.show_result);
                    videoview.setAdapter(videoAdapter);
                    videoview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String s;
                            int j = position;
                            VideoList videos = new VideoList(videoList,j);
                            Intent intent = new Intent(sousuo.this, VideoShow.class);
                            intent.putExtra("video",videos);
                            intent.putExtra("user1",user);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);

        user = (User) getIntent().getSerializableExtra("user");

        editText = (EditText) findViewById(R.id.sou_text);
        news = (Button) findViewById(R.id.sou_news);
        music = (Button) findViewById(R.id.sou_music);
        video = (Button) findViewById(R.id.sou_video);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                newsList.clear();
                text = editText.getText().toString();
                String url = "http://10.0.2.2:8080/se_news.action?text="+text;
                i = 1;
                send(url,handler,i);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicList.clear();
                text = editText.getText().toString();
                String url = "http://10.0.2.2:8080/se_music.action?text="+text;
                i = 2;
                musicList.clear();
                send(url,handler,i);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                videoList.clear();
                text = editText.getText().toString();
                String url = "http://10.0.2.2:8080/Sou_video.action?text="+text;
                i = 3;
                videoList.clear();
                send(url,handler,i);
            }
        });
    }

    @Override
    public void finish() {
        mediaPlayer.reset();
        super.finish();
    }

    //初始化音乐文件
    public void initmusic(String path){
        try{
            String c;
            File file = new File(Environment.getExternalStorageDirectory(),path);
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void send(final String url, final Handler handler,final int i){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = okHttpClient.newCall(request).execute();
                    String data = response.body().string();
                    pare(data,i);
                    Message message = new Message();
                    if (i == 1) message.what = 1;
                    if (i == 2) message.what = 2;
                    if (i == 3) message.what = 3;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void pare(final String data,final int i){
        try {
            JSONArray jsonArray = new JSONArray(data);

            if (i == 1){
                for (int j = jsonArray.length()-1;j>=0;j--) {
                    JSONObject object = jsonArray.getJSONObject(j);

                    int id = object.getInt("id");
                    int g;
                    String name = object.getString("name");
                    String text = object.getString("text");
                    String time = object.getString("time");
                    String title = object.getString("title");
                    News news11 = new News(id,name, text, time, title);
                    newsList.add(news11);
                }
            }

            if (i == 2){
                for (int j = jsonArray.length()-1;j>=0;j--) {
                    JSONObject object = jsonArray.getJSONObject(j);

                    int id = object.getInt("id");
                    String name = object.getString("name");
                    String path = object.getString("path");
                    String info = object.getString("information");

                    int h;

                    Music music1 = new Music(id,name,path,info);
                    musicList.add(music1);
                }
            }

            if (i == 3){
                for (int j = jsonArray.length()-1;j>=0;j--){
                    JSONObject object = jsonArray.getJSONObject(j);

                    int id = object.getInt("id");
                    String name = object.getString("name");
                    String path = object.getString("path");
                    String info = object.getString("info");
                    String s;

                    Video video = new Video(id,name,path,info);
                    videoList.add(video);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
