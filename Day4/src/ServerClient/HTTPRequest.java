package ServerClient;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class HTTPRequest {

    private String verb_;
    private String parameter_;
    private String header_;
    private String fileType_;

    //Create an input stream to read data from the socket
    private final InputStream inputStream_;

    private String fileName;

    //Create a HashMap named "headers" to store HTTP headers
    Map headers = new HashMap<String,String>();

    //Constructor that takes a Socket object as a parameter.
    public HTTPRequest(Socket socket) throws IOException {
        verb_=null;
        parameter_=null;
        header_=null;
        fileType_=null;
        //Initialize input stream, getting the inputStream from socket.
        inputStream_=socket.getInputStream();

    }

    //Getter methods
    //all of these get functions allow you to access the saved variable
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

    //This method is responsible for parsing the incoming HTTP request
    //It reads the request line and headers, populating the instance variables
    public Boolean parse() {
        //Create a Scanner object ('sc') to read from the input stream.
        Scanner sc = new Scanner(inputStream_);
        //Check if there is a next line to read
        if (sc.hasNext()) {
            //If so, split the request line by spaces into an array
            String[] inputArray = sc.nextLine().split(" ");
            //Ensure it contains three elements
            if( inputArray.length != 3)
                return false;
            //Store these elements in their respective instance variables.
            verb_ = inputArray[0];
            parameter_ = inputArray[1];
            header_ = inputArray[2];

            //Check if the parameter is a "/" and adjust it if so.
            if (parameter_.equals("/")) {
                parameter_ = "/";
            }
            //Determine the file type by finding the last dot in the parameter
            int i = parameter_.lastIndexOf('.');
            if (i > 0) {
                //Extract the file extension and store it in fileType
                fileType_ = parameter_.substring(i + 1);
                System.out.println(fileType_);
            }

            //Initialize boolean variable "done" for header parsing
            boolean done = false;

            while( !done ) {

                String requestLine = sc.nextLine();
                System.out.println(requestLine);
                //Checks that the request line isn't empty
                if( requestLine.length() != 0 ) {
                    //Split each line into key-value pairs based on the colon and space.
                    String[] pieces = requestLine.split(": ");
                    String key = pieces[0];
                    String value = pieces[1];
                    //Store the pairs into the headers map.
                    headers.put(key, value);
                }
                else {
                    //Indicating the end of the headers.
                    done = true;
                }
            }

        }
        //Returns true to indicate a successful parse.
        return true;
    }

    //Checks if the "Sec-WebSocket-Key" header is present in request headers.
    public Boolean isWebSocket(){

        boolean isKeyPresent = headers.containsKey("Sec-WebSocket-Key");
        return isKeyPresent;
    }

    //Retrieves the value of the "Sec-WebSocket-Key" header from the request headers.
    public String getWebSocketKey()
    {
        return (String)headers.get("Sec-WebSocket-Key");
    }

}


