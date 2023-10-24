import java.io.*;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;
import ServerClient.MyRunnable;


public class Main {
    public static void main(String[] args) throws IOException {

        //Create a ServerSocket object named server that listens on port 8080 for incoming client connections.
        ServerSocket server = new ServerSocket(8080);

        //Enter an infinite loop to continuously listen for incoming client connections.
        while (true) {
            Socket client = server.accept();
            //Create a Scanner object named sc to read from the input stream of the client socket.
            Thread thread = new Thread(new MyRunnable(client));
            thread.start();
            }
        }
    }
