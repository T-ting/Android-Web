package com.example.android.Activity.ReporterActivity.UserActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
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

public class IndexActivity extends AppCompatActivity{

    private Button button_news,music,video;//三大模块相对应的按钮
    private Button shuaxin; //刷新按钮
    private Button button;//签到按钮
    private TextView show; //文本 显示正在播放的音乐的名字
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private DrawerLayout drawerLayout;
    private Music music1;//临时对象变量 存放下一首，上一首，暂停播放相对应的music对象
    int po_id,s_id,z_id;//临时变量 分别存放下一首，上一首，暂停播放的listview所展示的音乐的相对应子项位置

    private int n =0,m=0,v1=0; //限制加载次数，防止重复点击重复加载
    private int s = 0; //模拟状态码 判断刷新哪块内容

    private List<Music> musicList = new ArrayList<>();
    private List<News> newsList = new ArrayList<>();
    private List<Video> videoList = new ArrayList<>();

    private User user = null;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    NewsAdapter newsAdapter = new NewsAdapter(IndexActivity.this,R.layout.index_item,newsList);
                    ListView listView = (ListView) findViewById(R.id.index_recyler);
                    listView.setAdapter(newsAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news = newsList.get(position);

                            Intent intent = new Intent(IndexActivity.this,ShowNews.class);
                            intent.putExtra("user",user);

                            intent.putExtra("news",news.getText());
                            intent.putExtra("id",news.getId());
                            startActivity(intent);
                        }
                    });
                    break;
                case 2:
                    MusicAdapter musicAdapter = new MusicAdapter(IndexActivity.this,R.layout.music_item,musicList);
                    ListView listView1 = (ListView) findViewById(R.id.index_recyler);
                    listView1.setAdapter(musicAdapter);
                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Music music = musicList.get(position);
                            //Toast.makeText(IndexActivity.this,position,Toast.LENGTH_SHORT).show();
                            show.setText(music.getName());
                            z_id = position;
                            //下一首
                            if (position < musicList.size() - 1) {
                                po_id = position + 1;
                            }
                            else {
                                po_id = 0;
                            }
                            //上一首
                            if (position != 0){
                                s_id = position-1;
                            }
                            else {
                                s_id = musicList.size() -1;
                            }
                           // music1 = musicList.get(po_id);
                            //music1 = musicList.get(position+1);

                            if (ContextCompat.checkSelfPermission(IndexActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(IndexActivity.this,new String[]{
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },1);
                            }else {
                                mediaPlayer.reset();
                                ini(music.getPath());
                            }

                            if (!mediaPlayer.isPlaying()){
                                mediaPlayer.start();
                            }
                            else {
                                mediaPlayer.reset();
                            }

                        }
                    });
                    break;
                case 3:
                    VideoAdapter videoAdapter = new VideoAdapter(IndexActivity.this,R.layout.video_item,videoList);
                    ListView listView2 = (ListView) findViewById(R.id.index_recyler);
                    listView2.setAdapter(videoAdapter);
                    listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            mediaPlayer.reset();
                            int i = position;
                            VideoList videos = new VideoList(videoList,i);
                            Intent intent = new Intent(IndexActivity.this, VideoShow.class);
                            intent.putExtra("video",videos);
                            intent.putExtra("user1",user);
                            startActivity(intent);

                        }
                    });
                    break;
            }
        }
    };

    //新闻web端路径
    String url = "http://10.0.2.2:8080/SelectAllNews";

    //音乐web端路径
    String mu_url = "http://10.0.2.2:8080/S_music";

    //视频web路径
    String vi_url = "http://10.0.2.2:8080/Se_video";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //获取传过来的值
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        //找各个相应的控件
        button_news = (Button) findViewById(R.id.button_news);
        music = (Button) findViewById(R.id.button_music);
        video = (Button) findViewById(R.id.button_video);
        show = (TextView) findViewById(R.id.show);
        shuaxin = (Button) findViewById(R.id.shuaxin);

        ImageView xys = (ImageView) findViewById(R.id.xys);
        ImageView zt = (ImageView) findViewById(R.id.zt);
        ImageView sys = (ImageView) findViewById(R.id.sys);

        //下一首
        xys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                z_id = po_id;
                if (po_id == 0) {
                    s_id = musicList.size() - 1;
                } else {
                    s_id = po_id - 1;
                }
                music1 = musicList.get(po_id);
                show.setText(music1.getName());
                Toast.makeText(IndexActivity.this, music1.getPath(), Toast.LENGTH_SHORT).show();
                mediaPlayer.reset();
                ini(music1.getPath());
                mediaPlayer.start();

                if (po_id < musicList.size() - 1) {
                    po_id++;
                } else {
                    po_id = 0;
                }

            }
        });

        //上一首
        sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                z_id = s_id;
                if (s_id < musicList.size() - 1) {
                    po_id = s_id + 1;
                } else {
                    po_id = 0;
                }
                music1 = musicList.get(s_id);
                show.setText(music1.getName());
                Toast.makeText(IndexActivity.this, music1.getPath(), Toast.LENGTH_SHORT).show();
                mediaPlayer.reset();
                ini(music1.getPath());
                mediaPlayer.start();

                if (s_id > 0) {
                    s_id--;
                } else {
                    s_id = musicList.size() - 1;
                }
            }
        });

        //暂停 播放
        zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                } else {
                    mediaPlayer.pause();
                    //mediaPlayer.reset();
                   // ini(musicList.get(z_id).getPath());
                }
            }
        });

        //加载新闻
        button_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = 1;
                newsList.clear();
                /*if (n == 0) {

                    n = 1;

                    *//*m = 0;
                    v1 = 0;*//*
                }*/
                send1(url, handler);

            }
        });

        //加载音乐
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = 2;
                musicList.clear();
                /*if (m == 0) {

                    m = 1;
                   *//* n = 0;
                    v1 = 0;*//*
                }*/
                send1(mu_url, handler);
            }
        });

        //加载视频
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = 3;
                videoList.clear();
                /*if (v1 == 0) {

                    v1 = 1;
                    *//*n = 0;
                    m = 0;*//*
                }*/
                send1(vi_url, handler);
            }
        });

        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s == 0){
                    Toast.makeText(IndexActivity.this,"请选择要刷新的内容",Toast.LENGTH_LONG).show();
                }
                else {
                    if (s == 1){
                        send1(url,handler);
                    }
                    else {
                        if (s == 2){
                            send1(mu_url,handler);
                        }
                        else {
                            send1(vi_url,handler);
                        }
                    }
                }
            }
        });

        //加载滑动菜单栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.user_nav_view);

        //加载头布局和菜单栏
        navigationView.inflateHeaderView(R.layout.nav_header);
        navigationView.inflateMenu(R.menu.nav_menu);

        //加载头布局 并设置监听事件
        View header = navigationView.getHeaderView(0);
        TextView username = (TextView) header.findViewById(R.id.username);
        username.setText(user.getName());
        final TextView tishi = (TextView) header.findViewById(R.id.tishi);
        button = (Button) header.findViewById(R.id.qiandao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = "http://10.0.2.2:8080/UserAdd?id="+user.getId();
                String text = (String) button.getText();
                if (text.equals("签到")) {
                    send1(u, handler);
                    button.setText("已签到");
                }
                else {
                    tishi.setText("今天已签到！请明天再来！");
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.zhanghu);
        }
        //设置默认选中菜单栏的子项
        navigationView.setCheckedItem(R.id.My);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                onOptionsItemSelected(item);
                Toast.makeText(IndexActivity.this,"test",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void finish() {
        mediaPlayer.reset();
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    //菜单栏里面的子项的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //个人信息
            case R.id.My:
                Intent intent = new Intent(IndexActivity.this,InfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                break;
            //收藏
            case R.id.collection:
                Intent intent1 = new Intent(IndexActivity.this,MyCollection.class);
                intent1.putExtra("user",user);
                startActivity(intent1);
                break;
            //我的评论
            case R.id.sousuo1:
                Intent intent2 = new Intent(IndexActivity.this,sousuo.class);
                intent2.putExtra("user",user);
                startActivity(intent2);
                break;
        }
        return true;
    }

    //初始化音乐文件
    public void ini(String path){
        try{
            File file = new File(Environment.getExternalStorageDirectory(),path);
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //发送请求，并通过Handel将接受的数据加载更新到UI线程
    public void send1(final String url, final Handler handler){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String rs = response.body().string();

                    Message message = new Message();
                    if (url.equals("http://10.0.2.2:8080/SelectAllNews")){
                        if (rs.equals("[{}]")){
                            message.what = 4;
                        }
                        else {
                                pa(rs);
                            message.what = 1;
                        }
                    }
                    else {
                        if (url.equals("http://10.0.2.2:8080/S_music")){
                            if (rs.equals("[{}]")){
                                message.what = 5;
                            }
                            else {
                                pamusic(rs);
                                message.what = 2;
                            }
                        }
                        else {
                            pavideo(rs);
                            message.what = 3;
                        }
                    }

                    handler.sendMessage(message);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }


    //解析新闻
   public void pa(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i =jsonArray.length()-1; i >= 0; i--) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                Log.d("xcv", String.valueOf(id));
                String name = jsonObject.getString("name");
                String text = jsonObject.getString("text");
                String time = jsonObject.getString("time");
                String title = jsonObject.getString("title");
                News news11 = new News(id,name, text, time, title);
                newsList.add(news11);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //解析音乐
    private void pamusic(String mu){
        try{
            JSONArray jsonArray = new JSONArray(mu);

            for (int j = jsonArray.length()-1; j >= 0;j--){
                JSONObject jsonObject = jsonArray.getJSONObject(j);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String path = jsonObject.getString("path");
                String info = jsonObject.getString("information");

                Music music = new Music(id,name,path,info);
                musicList.add(music);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //解析视频
    private void pavideo(String vi){
        try {
            JSONArray jsonArray = new JSONArray(vi);

            for (int t = jsonArray.length()-1;t >= 0;t--){
                JSONObject jsonObject = jsonArray.getJSONObject(t);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String path = jsonObject.getString("path");
                String info = jsonObject.getString("info");

                Video video = new Video(id,name,path,info);
                videoList.add(video);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
