package com.example.androidchatclient;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyWebsocket extends WebSocketAdapter {
//    @Override
//    public void onSendingHandshake(WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {
//        super.onSendingHandshake(websocket, requestLine, headers);
//    }

    final String MsgTag = "MyWebsocket: LH";


    public static Boolean wsOpen = false;
    private ChatRoomPage chatRoomPage;
    private List<String> users = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    public MyWebsocket(ChatRoomPage chatRoomPage) {
        this.chatRoomPage = chatRoomPage;
    }

    @Override
    //SENDING BACK
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        Log.d("Activity_main_chat", "ws open");
        super.onConnected(websocket, headers);
        wsOpen = true;

    }
    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        super.onTextMessage(websocket, text);
        Log.d("Activity_main_text", text);

        //Step 1: Parse out message like we did in 'on message' in JS
        // Extract message details from the received text
        JSONObject jsonObject = new JSONObject(text);
        String type = jsonObject.getString("type");
        Log.d("Activity_main_msg", type);

        String room = jsonObject.getString("room");
        Log.d("Activity_main_msg", room);

        if(type.equalsIgnoreCase("join")){
            String user = jsonObject.getString("user");
            Log.d("Join_user_name", user);
            chatRoomPage.displayJoin(user);
        }
        if(type.equalsIgnoreCase("message")){
            String user = jsonObject.getString("user");
            Log.d("Message_user_name", user);
            String message = jsonObject.getString("message");
            Log.d("Message_user_name", message);
            chatRoomPage.displayMessage(user,message);
        }


//        if(type.equalsIgnoreCase("join")){
//            String user = jsonObject.getString("user");
//            Log.d("Join_user_name", user);
//            users.add(user);
//            chatRoomPage.displayJoin(user);
//        }

        else{
            Log.d("Join_user_name_Failed", type);
        }

// Determine the content of the message based on its type
//        if (type.equals("join")) {
//            String message = jsonObject.getString("message");
//            Log.d("Join Message Came from server", message);
//            users.add(user);
//            chatRoomPage.displayJoin((user));
//        }
//        else if (type.equals("message")) {
//            String message = jsonObject.getString("message");
//            Log.d("Message Came from server", message);
//            //ARRAY
//            messages.add(message);
//            chatRoomPage.displayMessage(user,messages);
//            Log.d("Activity_main_msg", messages.toString());
//        } else if (type.equals("leave")) {
//            chatRoomPage.handleLeave(user);
//        }
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause){
        try{
            super.onError(websocket, cause);
            wsOpen = false;
        }
        catch (Exception e){
            Log.e("Activity_main_chat", "Error in MyWebSocket!");
            Log.e("Activity_main_chat", e.getLocalizedMessage());
        }

    }


}
