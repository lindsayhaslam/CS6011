package ServerClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ChatRoom {
    //Declare an empty ArrayList of MyRunnable Objects.
    private List<MyRunnable> clientArr = new ArrayList<>();
    //To store the name of the chat room.
    String name_;
    //Constructor that takes in a name parameter.
    //Gets initialized into name_.
    ChatRoom(String name){
        name_=name;
    }

    //Declare public and synchronized method that takes in a method parameter.
    //This method is used to send a message to all clients in the chat room.
    public synchronized void helperSend(String message) {
        //For-each loop that iterates over the MyRunnable objects in clientArr list.
        for (MyRunnable client : clientArr) {
            //This line calls the encodeMessage method on each client to send them the message.
            client.encodeMessage(message);
        }
    }
    //Declares public and synchronized method that takes in a MyRunnable object as a parameter.
    //Used to add client to the chat room.
    public synchronized void addClient(MyRunnable runnable) {
        //For-each loop that iterates over the MyRunnable client objects in the clientArr list.
        for(MyRunnable client : clientArr){
            //Retrieves the username of each existing client.
            String name =client.getUsername_();
            //Encodes and sends a join message to the newly added client based on info
            //From existing client
            runnable.encodeMessage(MyRunnable.makeJoinMsg(client.getRoomName_(), client.getUsername_()));
        }
        //The new client represented by 'runnable' is added to the 'clientArr' list.
        clientArr.add(runnable);
        //A join message is sent to all clients in the chat room.
        helperSend(MyRunnable.makeJoinMsg(runnable.getRoomName_(), runnable.getUsername_()));
    }

    //Declare a public and synchronized method that takes in a MyRunnable object as a parameter.
    //This is used to remove a client from the chat room.
    public synchronized void removeClient(MyRunnable runnable) {
        //removes specific client from clientArr list.
        clientArr.remove(runnable);
        //A leave message is sent to all clients in chat room.
        helperSend(MyRunnable.makeLeaveMsg(runnable.getRoomName_(), runnable.getUsername_()));
    }
    //Declare public and synchronized method that takes in a MyRunnable object as parameters.
    //Used to send a message from a client to all clients in chat room.
    public synchronized void sendMessage(MyRunnable runnable, String msg){
        //Sends the message, along with information about the sending client, to all clients in the chat room.
        helperSend(MyRunnable.makeMsgMsg(runnable.getRoomName_(), runnable.getUsername_(), msg));
    }
}
