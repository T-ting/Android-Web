package com.example.android.Activity.ReporterActivity.UserActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.MainActivity;
import com.example.android.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText num,pass,pass2,name;
    private Button submit;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case 2:
                    Toast.makeText(RegisterActivity.this,"该账户已经存在!!!",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        num = (EditText) findViewById(R.id.num);
        pass = (EditText)findViewById(R.id.pass);
        pass2 = (EditText) findViewById(R.id.pass2);
        name = (EditText) findViewById(R.id.name);
        submit = (Button) findViewById(R.id.register);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(num.getText().toString());
                String password = pass.getText().toString();
                String p2 = pass2.getText().toString();
                String username = name.getText().toString();

                if (password.equals(p2)){
                    String url ="http://10.0.2.2:8080/action/UserAction/userRegister.action?number="+id+"&&password="+password+"&&name="+username;
                    send(url,handler);
                }
                else {
                    Toast.makeText(RegisterActivity.this,"两次密码不一致！！！",Toast.LENGTH_LONG).show();
                }

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
                    String result = response.body().string();
                    Log.d("regi",result);
                    Message message = new Message();
                    if(result.equals("[{}]")){
                        message.what = 2;
                    }
                    else {
                        message.what = 1;
                    }
                    handler.sendMessage(message);

                }catch (Exception e){}
            }
        }).start();
    }
}
