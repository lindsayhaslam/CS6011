package com.example.androidchatclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainChat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // Retrieve the room name from the Intent
        String roomName = getIntent().getStringExtra(MainActivity.RoomNameKey);

        // Display the room name in your MainChat layout
        TextView roomNameTextView = findViewById(R.id.roomCarry);
        roomNameTextView.setText("Room: " + roomName);
    }

    public void handleSendButton(View view) {
        //Log.d(SendMsTag, "Button was pressed...");

        EditText messageEditText = findViewById(R.id.messageInputField);
        String messageSent = messageEditText.getText().toString();

        Intent intent = new Intent(this, MainChat.class);
        intent.putExtra("message",messageSent);
        startActivity(intent);
    }
}