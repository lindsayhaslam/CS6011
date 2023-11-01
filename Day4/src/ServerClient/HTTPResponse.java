package ServerClient;

import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Base64;

public class HTTPResponse {

    //Method takes a filename as a parameter and returns a File object in the src directory
    public File createFile(String filename){
        return new File ("Resources/src/" + filename);
    }

    //Method takes a Socket object, a File object, and String as parameters.
    //This method sends an HTTP response to the client based on the file type provided.
    public void sendHTTPResponse (Socket client, File file, String fileType) throws IOException {
        //Create an output stream from the client's socket
        OutputStream outStream = client.getOutputStream();
        FileInputStream fileStream = null;
        //Checks if the fileType is null.
        if( fileType != null )
            //Create a FileInputStream to read the content of the file.
            fileStream = new FileInputStream(file);
        //WRite an HTTP response header indicating a successful response.
        outStream.write("http/1.1 200 Success \n".getBytes());
        //Depending on the fileType, set the "Content-type" header in the response accordingly.
        if (fileType.equals("jpeg")) {
            outStream.write(("Content-type: image/" + fileType + "\n").getBytes());
        }
        else if (fileType.equals("pdf")) {
            outStream.write(("Content-type: application/" + fileType + "\n").getBytes());
        }
        else {
            outStream.write(("Content-type: text/" + fileType + "\n").getBytes());
        }
        //Write a newline character to separate the header from the file's content
        outStream.write("\n".getBytes());
        //Transfer the content of the file to the outStream using "transferTo"
        fileStream.transferTo(outStream);
        //Flush the output stream and close it.
        outStream.flush();
        outStream.close();
    }

    //This method performs the WebSocket handshake. It takes a Socket object (client) and String (key) as parameters.
    public void sendWebSockHandshake(Socket client, String key) throws IOException {
        //Create an output stream ('outStream') from the client's socket
        OutputStream outStream = client.getOutputStream();

        // Compute the Sec-WebSocket-Accept response for the key provided.
        //This involves appending a magic string to the key, hashing it using
        //SHA-1, and base64 encoding it.
        key += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"; // Magic string
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(key.getBytes("UTF-8"));
            key = Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Send the HTTP 101 response indicating a successful WebSocket handshake with appropriate headers.
        outStream.write("HTTP/1.1 101 Switching Protocols\r\n".getBytes());
        outStream.write("Upgrade: websocket\r\n".getBytes());
        outStream.write("Connection: Upgrade\r\n".getBytes());
        outStream.write(("Sec-WebSocket-Accept: " + key + "\r\n").getBytes());
        outStream.write("\r\n".getBytes());
        //Flush the outputStream
        outStream.flush();
    }

    //sendFailResponse takes a Socket object (client) as a parameter.
    //This method sends a failure HTML response to the client.
    public void sendFailResponse(Socket client) throws IOException {
        //Create a new File type called "failFile" with the address of failFile.html
        File failFile = new File ("Resources/src/failFile.html");
        //Create an output stream from the client's socket
        OutputStream outStream = client.getOutputStream();
        //Create a FileInputStream to read the content of the failure file.
        FileInputStream failFileStream = new FileInputStream(failFile);

        //Write HTTP response headers indicating success
        outStream.write("HTTP/1.1 200 OK\n".getBytes());
        //Set the "Content-type" header to "text/html"
        outStream.write("Content-type: text/html\n".getBytes());
        //Add newline character to separate the header from the file content
        outStream.write("\n".getBytes("UTF-8"));
        //Transfer content of failure file to the outStream using transferTo.
        failFileStream.transferTo(outStream);
        //Flush the output stream and close it.
        outStream.flush();
        outStream.close();

    }

}