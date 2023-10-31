package com.example.hello2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final String MsTag = "MainActivity:Lh";
    static final String RoomNameKey = "RoomNameKey";
    boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleButton(View view) {
        Log.d(MsTag, "Button was pressed...");
        EditText tv = findViewById(R.id.textView);
        if (firstClick) {
            Log.d(MsTag, "in here...");

            tv.setText("6011");
            firstClick = false;
        } else {
            String roomName = tv.getText().toString();
            Intent intent = new Intent(this, MainChat.class);
            intent.putExtra(RoomNameKey, roomName);
            startActivity(intent);
        }


    }
}