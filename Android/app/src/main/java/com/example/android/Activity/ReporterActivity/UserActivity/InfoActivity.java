package com.example.android.Activity.ReporterActivity.UserActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.android.Model.Reporter;
import com.example.android.Model.User;
import com.example.android.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        User user = (User) getIntent().getSerializableExtra("user");

        TextView num = (TextView) findViewById(R.id.number);
        TextView name = (TextView) findViewById(R.id.name);
        TextView pas = (TextView) findViewById(R.id.pas);
        TextView money = (TextView) findViewById(R.id.yinbi);


        num.setText(String.valueOf(user.getId()));
        name.setText(user.getName());
        pas.setText(user.getPassword());
        money.setText(String.valueOf(user.getMoney()));
    }
}
