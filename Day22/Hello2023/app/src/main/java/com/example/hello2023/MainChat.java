package com.example.hello2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        Button btn = findViewById(R.id.mainChatBtnId);
        String info = "Nothing";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            info = extras.getString(MainActivity.RoomNameKey);
        }
        btn.setText(info);
    }

}