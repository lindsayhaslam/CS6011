package ServerClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class HTTPRequest {

    private String verb_;
    private String parameter_;
    private String header_;
    private String fileType_;

    private String fileName;

    //For storing the input stream from the client's socket
    private final InputStream inputStream_;
    Map headers = new HashMap<String, String>();

    //Constructor that takes in a Socket
    public HTTPRequest(Socket client) throws IOException {
        verb_ = null;
        parameter_ = null;
        header_ = null;
        fileType_ = null;
        //Set up inputStream_ to read data from the client.
        inputStream_ = client.getInputStream();
    }

    //"Getter" methods
    //all of these allow you to access the saved variable.
    public String getVerb(){
        return verb_;
    }
    public String getParameter(){
        return parameter_;
    }
    public String getHeader(){
        return header_;
    }
    public String getFileType(){
        return fileType_;
    }

    public Boolean parseRequest() {
        //Scanner needed to read and parse request.
        Scanner scanner = new Scanner(inputStream_);
        //Check if there is a line in the request.
        if (scanner.hasNext()) {
            //Split the first line of the request into an array using spaces.
            String[] inputArray = scanner.nextLine().split(" ");
            if(inputArray.length != 3)
                return false;
            verb_ = inputArray[0];
            parameter_ = inputArray[1];
            header_ = inputArray[2];

            if (parameter_.equals("/")) {
                parameter_ = "/";
            }
            //Extract the file type and store it in fileType_
            int i = parameter_.lastIndexOf('.');
            if (i > 0) {
                fileType_ = parameter_.substring(i + 1);
                System.out.println(fileType_);
            }

            //Set done to false
            boolean done = false;
            //Loop through and read to parse the HTTP headers.
            //Continues to read until it encounters an empty line.
            while (!done) {

                String request = scanner.nextLine();
                System.out.println(request);
                if (request.length() != 0) {
                    String[] pieces = request.split(": ");
                    String key = pieces[0];
                    String value = pieces[1];
                    //Place key and value pair into the headers map
                    headers.put(key, value);
                } else {
                    done = true;
                }
            }
        }
        return true;
    }

    //Checks if the request is a Websocket by looking for "Sec-WebSocket-Key"
    public Boolean isWebSocket()
    {
        boolean isKeyPresent = headers.containsKey("Sec-WebSocket-Key");
        return isKeyPresent;
    }
    //Retrieves the value of the "Sec-WebSocket-Key" header.
    //This will be used in the WebSocket handshake.
    public String getWebSocketKey()
    {
        return (String)headers.get("Sec-WebSocket-Key");
    }

//    public String getFileName() {
//        return fileName;
//    }
}