package com.example.androidchatclient;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String MsTag = "MainActivity:Lh";
    public EditText username;
    public EditText room;
    public TextView error;

    public static final String roomNameKey = "roomNameKey";
    public static final String userNameKey = "userNameKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //invokes the superclass's 'onCreate' method
        super.onCreate(savedInstanceState);
        //sets the content view of the activity layout
        setContentView(R.layout.activity_main);
        //By ID, usernameFld is added to username
        username = findViewById(R.id.usernameFld);
        //By ID, roomFld is added to room
        room = findViewById(R.id.roomFld);
        //By ID, error is added to error
        error = findViewById(R.id.error);
    }

    //For onClick event
    public void handleLogin(View view) {
        //Retrieve text entered by the user and put it in their checks
        String userCheck = String.valueOf(username.getText());
        String roomCheck = String.valueOf(room.getText());
        //Check that inputs are valid
        boolean userHasLtrs = userCheck.matches(".*[a-zA-Z].*");
        boolean roomHasLtrs = roomCheck.matches(".*[a-zA-Z].*");
        //If there were inputs
        if (userHasLtrs && roomHasLtrs) {
            Intent intent = new Intent(this, ChatRoomPage.class);
            //This line adds an extra piece of data to the intent
            intent.putExtra(roomNameKey, roomCheck);
            //This line adds an extra piece of data to the intent
            intent.putExtra(userNameKey, userCheck);
            //Start the activitiy defined by the intent
            Log.i("Starting","Activity");
            startActivity(intent);
        } else {
            //Instruct user to input all required fields
            error.setText("Please fill all required fields.");
        }
    }
}