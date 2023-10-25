package ServerClient;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyRunnable implements Runnable {
    private Socket client;

    public MyRunnable(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            HTTPRequest request = new HTTPRequest(client);
            String filename = request.parseRequest();
            if (filename != null) {
                HTTPResponse response = new HTTPResponse(client, filename);
                response.sendResponse();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}