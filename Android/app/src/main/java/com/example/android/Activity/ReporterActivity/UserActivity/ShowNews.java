package com.example.android.Activity.ReporterActivity.UserActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.android.Adapter.PingLunAdapter;
import com.example.android.Model.Comments;
import com.example.android.Model.News;
import com.example.android.Model.SC;
import com.example.android.Model.User;
import com.example.android.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//新闻内容
public class ShowNews extends AppCompatActivity {

    private List<Comments> commentsList1 = new ArrayList<>();
    private SC sc;
    private int i =0;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    PingLunAdapter adapter1 = new PingLunAdapter(ShowNews.this,R.layout.comments_item,commentsList1);
                    ListView listView1 = (ListView) findViewById(R.id.news_list1);
                    listView1.setAdapter(adapter1);
                    break;
                case 2:
                    Toast.makeText(ShowNews.this,"投币成功",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(ShowNews.this,"已收藏",Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(ShowNews.this,"投币失败，您的硬币数不足",Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(ShowNews.this,"评论成功",Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(ShowNews.this,"评论失败",Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    Toast.makeText(ShowNews.this,"取消收藏",Toast.LENGTH_SHORT).show();
                    break;
                case 8:
                    Toast.makeText(ShowNews.this,"操作失败，请查看网络！",Toast.LENGTH_SHORT).show();
                    break;
                default:
                        break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        final EditText editText = (EditText) findViewById(R.id.c_text);
        final LinearLayout collection = (LinearLayout) findViewById(R.id.collection);
        Button button = (Button) findViewById(R.id.post);
        TextView textView = (TextView) findViewById(R.id.text111);

        //获取上个活动传送过来的数据
        Intent intent1 = getIntent();
        String text = intent1.getStringExtra("news");//新闻内容
        final int id = intent1.getIntExtra("id",0);//新闻id
        final User user = (User) intent1.getSerializableExtra("user");//用户信息

        //获取评论
        String url = "http://10.0.2.2:8080/select.action?news_id="+id;
        getComments(url,handler);

        //加载新闻内容
        textView.setText(text);

        //发表评论
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                String date = simpleDateFormat.format(new Date());

                String pl = editText.getText().toString();
                String url2 = "http://10.0.2.2:8080/userWriter.action?pl="+pl+"&&news_id="+id+"&&id="+user.getId();
                sendpl(url2,handler);
                Comments comments = new Comments(user.getName(),pl,date);
                commentsList1.add(comments);
            }
        });

        //点赞
        final LinearLayout dianzan = (LinearLayout) findViewById(R.id.dianzan);
        dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianzan.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        });

        //投币
        final LinearLayout toubi = (LinearLayout) findViewById(R.id.toubi);
        toubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toubi.setBackgroundColor(Color.parseColor("#0066FF"));
                int id = user.getId();
                Log.d("iff",String.valueOf(id));
                String url1 = "http://10.0.2.2:8080/UserPush?id="+id;
                int i =1;//判断调用哪个功能
                sendhanshu(url1,handler,i);
            }
        });

        //收藏
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String url1 = "http:/10.0.2.2:8080/UserCollection.action?news_id=" + id + "&&user_id=" + user.getId();

                    if (i == 0) {
                        sendhanshu(url1, handler, i);
                        collection.setBackgroundColor(Color.parseColor("#CC00CC"));
                        i = 2;
                    }
                    else  {
                        String url2 = "http://10.0.2.2:8080/de_sc.action?news_id=" + id + "&&user_id=" + user.getId();
                        sendhanshu(url2,handler,i);
                        collection.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        i= 0;
                    }
            }
        });

    }

    //查询该新闻的状态码 以确定该新闻是否已被该用户收藏
    private void getstatus(final String url2 , final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient5 = new OkHttpClient();
                    Request request = new Request.Builder().url(url2).build();
                    Response response = okHttpClient5.newCall(request).execute();
                    String data11 = response.body().string();
                    parsta(data11);

                    Message message = new Message();
                    //message.what =
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
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
                    parea(re);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //点击函数 访问web端并发送相应数据
    private void sendhanshu(final String url1,final Handler handler1,final int i){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient okHttpClient1 = new OkHttpClient();
                    Request request1 = new Request.Builder().url(url1).build();
                    Response response1 = okHttpClient1.newCall(request1).execute();
                    String re =response1.body().string();
                    Message message1 = new Message();
                    if ( i == 1){
                        if (re.equals("[{}]")){
                            message1.what = 4;
                        }
                        else {
                            message1.what = 2;
                        }
                    }
                    else {
                        if (i == 2){
                            if (re.equals("[{}]")) {
                                message1.what = 7;
                            }
                            else {
                                message1.what = 8;
                            }
                        }
                        else {
                            message1.what = 3;
                        }
                    }

                    handler1.sendMessage(message1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //评论内容送到服务端
    public void sendpl(final String u, final Handler handler2){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient3 = new OkHttpClient();
                    Request request3 = new Request.Builder().url(u).build();
                    Response response3 = okHttpClient3.newCall(request3).execute();
                    String rsq = response3.body().string();
                    Message message3 = new Message();
                    if (rsq.equals("[{}]")){
                        message3.what = 6;
                    }
                    else {
                        message3.what = 5;
                    }

                    handler2.sendMessage(message3);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //解析web服务端发送过来的评论内容
    private void parea(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i =jsonArray.length()-1; i >= 0; i--) {
                JSONObject object = jsonArray.getJSONObject(i);

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

    //解析收藏
    private void parsta(String data){
            try {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length() - 1; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int status = jsonObject.getInt("status");


                    sc = new SC(status);
                    Log.d("scn",sc.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
