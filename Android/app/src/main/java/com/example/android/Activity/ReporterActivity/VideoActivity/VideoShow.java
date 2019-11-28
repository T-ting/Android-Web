package com.example.android.Activity.ReporterActivity.VideoActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.android.Activity.ReporterActivity.UserActivity.IndexActivity;
import com.example.android.Activity.ReporterActivity.UserActivity.ShowNews;
import com.example.android.Adapter.PingLunAdapter;
import com.example.android.Model.Comments;
import com.example.android.Model.User;
import com.example.android.Model.Video;
import com.example.android.Model.VideoList;
import com.example.android.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoShow extends AppCompatActivity {

    private List<Comments> commentsList1 = new ArrayList<>();
    private VideoView videoView;
    private int s_id,x_id,z_id;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    PingLunAdapter adapter1 = new PingLunAdapter(VideoShow.this,R.layout.comments_item,commentsList1);
                    ListView listView1 = (ListView) findViewById(R.id.video_pl_list);
                    listView1.setAdapter(adapter1);
                    break;
                case 5:
                    Toast.makeText(VideoShow.this,"评论成功",Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(VideoShow.this,"评论失败",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_show);

        final EditText editText = (EditText) findViewById(R.id.c_text);
        Button button = (Button) findViewById(R.id.post);
        videoView = (VideoView) findViewById(R.id.video_View);
        final User user = (User) getIntent().getSerializableExtra("user1");
        VideoList videoList = (VideoList) getIntent().getSerializableExtra("video");
        int i = videoList.getI();
        final List<Video> videoList1 = videoList.getList();
        final Video video = videoList1.get(i);

        TextView show_info = (TextView) findViewById(R.id.show_info);
        show_info.setText(video.getInfo());

        final String url = "http://10.0.2.2:8080/select.action?video_id="+video.getId();
        getComments(url,handler);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String date = simpleDateFormat.format(new Date());

                String pl = editText.getText().toString();

                String u = "http://10.0.2.2:8080/userWriter.action?pl="+pl+"&&video_id="+video.getId()+"&&id="+user.getId();
                ShowNews showNews = new ShowNews();
                showNews.sendpl(u,handler);

                //实时显示评论
                Comments comments = new Comments(user.getName(),pl,date);
                commentsList1.add(comments);
            }
        });

        //上一首id
        if (i == 0){
            s_id = videoList1.size()-1;
        }
        else {
            s_id = -1;
        }

        //下一首id
        if (i == videoList1.size()-1){
            x_id = 0;
        }
        else {
            x_id = i+1;
        }

        ImageView xys = (ImageView) findViewById(R.id.xys);
        ImageView zt = (ImageView) findViewById(R.id.zt);
        ImageView sys = (ImageView) findViewById(R.id.sys);

        //下一首
        xys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentsList1.clear();
                z_id = x_id;//暂停id

                //更新上一首id
                if (x_id == 0){
                    s_id = videoList1.size()-1;
                }
                else {
                    s_id = x_id - 1;
                }

                Video video1 = videoList1.get(x_id);
                String url2 = "http://10.0.2.2:8080/select.action?video_id="+video1.getId();
                getComments(url2,handler);
                videoView.pause();
                ini_video(video1.getPath());
                videoView.start();

                ////更新下一首id
                if (x_id <videoList1.size()-1){
                    x_id++;
                }
                else {
                    x_id = 0;
                }
            }
        });

        //上一首
        sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentsList1.clear();

                z_id = s_id;//暂停id

                //更新下一首id
                if (s_id < videoList1.size() -1){
                    x_id = s_id+1;
                }
                else {
                    x_id = 0;
                }
                Video video1 = videoList1.get(s_id);
                String url1 = "http://10.0.2.2:8080/select.action?video_id="+video1.getId();
                getComments(url1,handler);
                videoView.pause();
                ini_video(video1.getPath());
                videoView.start();

                //更新一首id
                if (s_id >0){
                    s_id --;
                }
                else {
                 s_id = videoList1.size()-1;
                }
            }
        });

        //暂停 播放
        zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying()){
                    videoView.start();
                }
                else {
                    videoView.pause();
                    ini_video( videoList1.get(z_id).getPath());
                }
            }
        });

        if (ContextCompat.checkSelfPermission(VideoShow.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(VideoShow.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else {
            ini_video(video.getPath());
        }
        if (!videoView.isPlaying()){
            videoView.start();
        }
        if (videoView.isPlaying()){
            videoView.pause();
        }

    }

    //初始化视频文件
    public  void ini_video(String v_path){
        File file = new File(Environment.getExternalStorageDirectory(),v_path);
        videoView.setVideoPath(file.getPath());
    }

    //获取评论
    private void getComments(final String url,final Handler handler){
        new  Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = okHttpClient.newCall(request).execute();
                    String re =response.body().string();
                    String s;
                    parea(re);
                    Message message1 = new Message();
                    message1.what = 1;
                    handler.sendMessage(message1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parea(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i =jsonArray.length()-1; i >= 0; i--) {
                JSONObject object = jsonArray.getJSONObject(i);

                //int id = object.getInt("id");
                String name1 = object.getString("name");
                String text1 = object.getString("text");
                String time1 = object.getString("time");

                Comments comments = new Comments(name1,text1,time1);
                commentsList1.add(comments);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
