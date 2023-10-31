package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendMessage(View view){
        TextView textView = findViewById(R.id.textView); // Assuming you have a TextView with the ID "textView"
        textView.setText("What's up?");
        View layout = findViewById(R.id.background); // Assuming you have a layout with the ID "layout"
        layout.setBackgroundColor(Color.parseColor("#89CFF0"));
    }
}