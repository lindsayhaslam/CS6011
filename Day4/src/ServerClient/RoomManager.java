package ServerClient;

import java.util.ArrayList;
public class RoomManager {

    //Declare an array of ChatRoom objects, ready to store chat rooms
    ArrayList<ChatRoom> chatRmArr = new ArrayList<>();

    //Constructor for RoomManager, left empty
    public RoomManager() {
    }
    //Declare private method that takes roomName as a parameter
    //This is used to search for a chat room with a specific name
    private ChatRoom findRoom(String roomName) {

        //For-each loop that iterates over the 'ChatRoom' objects in the
        //"chatRmArr" list.
        for (ChatRoom room : chatRmArr) {
            //Check if the name_ field of the ChatRoom object matches the provided roomName.
            if (room.name_.equals(roomName)) {
                //If a match is found, this line returns the "ChatRoom" object.
                //The loop stops as soon as a matching room is found.
                return room;
            }
        }
        return null;
    }

    //Declare public method that takes a MyRunnable object as a parameter.
    //This is used for allowing a client to join a chat room.
    public void joinRoom(MyRunnable runnable) {

        //Calls the 'findRoom' method to search for a chat room with the name,
        //which is 'runnable.getRoomName_(). The result is stored in the room variable.
        ChatRoom room = findRoom(runnable.getRoomName_());

        //Checks if a chat room was found
        if (room != null) {
            //If the chat room exists, add the client (in runnable) to the room using "addClient".
            room.addClient(runnable);
        } else {
            //Create a new ChatRoom object
            ChatRoom newChatRoom = new ChatRoom(runnable.getRoomName_());
            //Add new chat room to the chatRmArr list.
            chatRmArr.add(newChatRoom);
            //The client (in runnable) is added to the new chat room.
            newChatRoom.addClient(runnable);
        }
    }
    //Declared method that allows the client to leave a chat room.
    public void leaveRoom(MyRunnable runnable) {
        //Calls 'findRoom' method to search for the chat room with the specified name
        //and stores the result in the 'room' variable.
        ChatRoom room = findRoom(runnable.getRoomName_());

        //Checks if chatRoom was found
        if (room != null) {
            //If line exists, call remove client method and removes client from room.
            room.removeClient(runnable);
        }

    }

    //Declare public sendMess method.
    //This allows for client to send a message in chat room.
    public void sendMess(String msg, MyRunnable runnable) {
        //Create new ChatRoom object
        //With the findRoom method, find the chatroom and store it in the "room" variable.
        ChatRoom room = findRoom(runnable.getRoomName_());
        //Checks if chatroom was found.
        if (room != null) {
            //If the chatroom exists, call the 'sendMessage' method.
            room.sendMessage(runnable, msg);
        }
    }
}