package com.example.androidchatclient;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ChatRoomPage extends AppCompatActivity {
    final String MsTag = "ChatRoomPage:Lh";
    public String username;
    public String room;
    public TextView roomName;
    public Button sendBtn;
    public EditText messageFld;
    public LinearLayout messageContainer;
    public LinearLayout userContainer;

    // Use ArrayList to store messages
    ListView messageListView;

    // Use ArrayList to store users in the room

    private static final String wsURL = "ws://10.0.2.2:8080/";
    private WebSocket ws = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Invokes the superclass's 'onCreate' method'
        super.onCreate(savedInstanceState);
        //Sets the content view of the activity to the layout defined in 'activity_main_chat.xml'
        setContentView(R.layout.activity_main_chat);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            username = extras.getString(MainActivity.userNameKey);
            room = extras.getString(MainActivity.roomNameKey);
        }

        roomName = findViewById(R.id.RmName);
        sendBtn = findViewById(R.id.sendBtn);
        messageFld = findViewById(R.id.msgFld);
        messageContainer = findViewById(R.id.messageContainer);
        userContainer = findViewById(R.id.usersFld);
        //Get username by userName ID

        roomName.setText("Room: " + room);

        //For users
        TextView userText = new TextView(this);
        userText.setPadding(50,10,10,10);
        userText.setText("Users: ");
        userContainer.addView(userText);
        messageListView = new ListView(ChatRoomPage.this);
        try {
            //Initialize using the WebSocketFactory.
            ws = new WebSocketFactory().createSocket(wsURL);
            //Add an event listener.
            ws.addListener(new MyWebsocket(this));
            //Connect the websocket asynchronously.
            ws.connectAsynchronously();
            while(MyWebsocket.wsOpen == false){

            }
            if(MyWebsocket.wsOpen){
                ws.sendText("join: " +username + " : " +room);
            }

        } catch (IOException e) {

            Log.d(MsTag, "Error in ChatRoomPage!");
        }


    }

    //Used onClick
    public void handleSend(View view) {
        //Retrieve message from message field, turn it into a string, and store it in messageText
        String messageText = messageFld.getText().toString();
        if (!messageText.equals(null)) {
            if(MyWebsocket.wsOpen){
                Log.d("Activity_main_chat", "Message to send: " + messageText);
                ws.sendText("message:" + username + ":" + room + ":" + messageText);
                messageFld.setText("");
            }
            else{
                Log.i("WebSocket no open",String.valueOf(MyWebsocket.wsOpen));
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public String getRoom() {
        return room;
    }

    public void displayMessage(final String username, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String user_ = username;
                Log.d("Main_chat_activity", "I am in display message!");
                //List View

                TextView messageView = new TextView(ChatRoomPage.this);
                messageView.setPadding(50, 10, 10, 50);
                messageView.setText(user_ + ": " + message);
                messageListView.addView(messageView);
                messageContainer.addView(messageView);
            }
        });
    }

    public void displayJoin(final String username) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String user = username;
                Log.d("Main_chat_activity", "I am in the display join!");
                TextView addedUser = new TextView(ChatRoomPage.this);
                addedUser.setPadding(50, 10, 10, 10);
                String existingText = addedUser.getText().toString();
                addedUser.setText(existingText+"  "+user);
                userContainer.addView(addedUser);
            }
        });
    }
    public void handleLeave(final String username) {
        // Handle a user leaving the chat
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Find and remove the view with the username from userContainer
                for (int i = 0; i < userContainer.getChildCount(); i++) {
                    View child = userContainer.getChildAt(i);
                    if (child instanceof TextView) {
                        TextView textView = (TextView) child;
                        if (textView.getText().toString().equals(username)) {
                            userContainer.removeView(textView);
                            break; // Exit the loop once the user is removed
                        }
                    }
                }
            }
        });
    }
}