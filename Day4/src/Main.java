import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import ServerClient.MyRunnable;
import ServerClient.RoomManager;


public class Main {
    //Entry point of the program, specify that the method may throw an exception
    public static void main(String[] args) throws IOException {

        //This line creates an empty "ArrayList". Not used?
        ArrayList<MyRunnable> runnArr = new ArrayList<>();
        //Creates a RoomManager object, used to manage chat rooms and clients.
        RoomManager roomManager = new RoomManager();
        //Creates a ServerSocket that listens on port 8080 for incoming client connections.
        ServerSocket server = new ServerSocket(8080);

        //Starts an infinite loop that will listen for incoming client connections.
        while (true) {
            //Inside this loop, accept incoming client connections
            //Assign those connections to client variable/Socket object
            Socket client = server.accept();
            //Create a new Thread object.
            //The MyRunnable object is responsible for handling communication with the client
            //Initializes the "client" socket and the "roomManager"
            Thread thread = new Thread(new MyRunnable(client, roomManager));
            //Starts the newly created thread, which will execute the run method.
            thread.start();
            }
        }
    }
