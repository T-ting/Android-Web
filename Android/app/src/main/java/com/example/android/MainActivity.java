package com.example.android;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.android.Activity.ReporterActivity.UserActivity.IndexActivity;
import com.example.android.Activity.ReporterActivity.UserActivity.RegisterActivity;
import com.example.android.Model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> userList = new ArrayList<>();
    private EditText editText1;
    private EditText editText2;
    private TextView reg;
    private Button button;
    private String url;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    Intent intent = new Intent(MainActivity.this,IndexActivity.class);
                    intent.putExtra("user",userList.get(0));
                    startActivity(intent);
                    break;
                case 3:
                    Toast.makeText(MainActivity.this,"登录失败！！账号或密码错误",Toast.LENGTH_LONG).show();
                    break;
                default:
                        break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.edit1);
        editText2 = (EditText) findViewById(R.id.edit2);
        button = (Button) findViewById(R.id.button);
        reg = (TextView) findViewById(R.id.re);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String num = editText1.getText().toString();
                final String pas = editText2.getText().toString();
                url = "http://10.0.2.2:8080/action/UserAction/userlogin.action?test="+num+"&&test1="+pas;

                send(url,handler);
            }
        });
    }

    private void send(final String url, final Handler handler) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String rs= response.body().string();
                    Message message = new Message();
                    if (rs.equals("[{}]")){
                        message.what = 3;
                    }
                    else {
                        pa_user(rs);
                        message.what = 1;
                    }

                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void pa_user(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String password = jsonObject.getString("password");
                int money = jsonObject.getInt("money");
                int collection = jsonObject.getInt("collection");

                User user1 = new User(id,name,password,money,collection);
                userList.add(user1);
                Log.d("user",userList.get(0).getName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
