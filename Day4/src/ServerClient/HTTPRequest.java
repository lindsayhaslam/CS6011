package ServerClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class HTTPRequest {
    Socket client;
    String filename = "";
    public HTTPRequest(Socket client) {
        this.client = client;
        this.filename = "";
    }

    public String parseRequest() throws IOException {
        Scanner scanner = new Scanner(client.getInputStream());
        if (scanner.hasNext()) {
            String request = scanner.nextLine();
            filename = request.split(" ")[1];
            return filename;
        }
        return null;
    }

    public String getFilename() {
        return filename;
    }
}