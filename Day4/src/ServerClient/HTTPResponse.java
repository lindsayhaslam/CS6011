package ServerClient;
import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class HTTPResponse {
        public File createFile(String filename){
            return new File ("Resources/src/" + filename);
        }

        //This method sends an HTTP response to client.
        public void sendResponse(Socket client, File file, String fileType) throws IOException {
            //Create the output stream to send data to the client through the provided "Socket"
            OutputStream outputStream = client.getOutputStream();
            FileInputStream fileStream = null;
        //Check if "fileType" is not null. If it is not null, open FileInputStream
        //to read the contents of the provided file.
        if( fileType != null )
            fileStream = new FileInputStream(file);
        //Response header
        outputStream.write("http/1.1 200 Success \n".getBytes());
        if (fileType.equals("jpeg")) {
            outputStream.write(("Content-type: image/" + fileType + "\n").getBytes());
        }
        else if (fileType.equals("mp3")) {
            outputStream.write(("Content-type: audio/" + fileType + "\n").getBytes());
        }
        else {
            outputStream.write(("Content-type: text/" + fileType + "\n").getBytes());
        }
        //Write a blank line to indicate the end of the response header
        outputStream.write("\n".getBytes());
        //Reads file, sends its contents byte by byte to the client through the output stream.
        //Iterates over the entire file length, reading each byte, and flushing the output.
        fileStream.transferTo(outputStream);
        //Flush and close the output stream.
        outputStream.flush();
        outputStream.close();
    }
    //Send a WebSocket response to client using "Socket" and a security key.
    public void sendWebSocketResponse(Socket client, String key) throws IOException {
            //Create OutputStream to send data to the client.
            OutputStream outStream = client.getOutputStream();
        // Compute the Sec-WebSocket-Accept response for the key
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
        // Send the HTTP 101 response, including the WebSocket upgrade headers
        outStream.write("HTTP/1.1 101 Switching Protocols\r\n".getBytes());
        outStream.write("Upgrade: websocket\r\n".getBytes());
        outStream.write("Connection: Upgrade\r\n".getBytes());
        outStream.write(("Sec-WebSocket-Accept: " + key + "\r\n").getBytes());
        outStream.write("\r\n".getBytes());
        //Flush the output stream
        outStream.flush();
    }

//    private void sendSuccessfulResponse(OutputStream outputStream, String extension) throws IOException {
//        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
//        outputStream.write(("Content-type: " + getContentType(extension) + "\n").getBytes());
//        outputStream.write("\n".getBytes());
//
//    }

    public void sendErrorResponse(Socket client) throws IOException {
        //Load failFile into new file object
        File failFile = new File ("/Users/lindsayhaslam/CS6011/Day4/Resources/src/failFile.html");
        //Create output stream to send data to the client.
        OutputStream outputStream = client.getOutputStream();
        //Create FileInputStream to read the contents of the error file.
        FileInputStream failFileStream = new FileInputStream(failFile);

        //Write an HTTP response header for a 404 error
        outputStream.write("HTTP/2.0 404 OK\n".getBytes());
        outputStream.write("Content-type: text/html\n".getBytes());
        outputStream.write("\n".getBytes());
        //Contents of failFile are transferred to outputStream
        failFileStream.transferTo(outputStream);
        //Flush and close the outputStream
        outputStream.flush();
        outputStream.close();
    }
//
//    private void sendFileContents(OutputStream outputStream, FileInputStream fileStream) throws IOException {
//        int bytesRead;
//        byte[] buffer = new byte[1024];
//        while ((bytesRead = fileStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//    }
//    private String getContentType(String extension) {
//        switch (extension) {
//            case "html":
//                return "text/html";
//            case "css":
//                return "text/css";
//            case "jpeg":
//                return "image/jpeg";
//            case "mp3":
//                return "audio/mpeg";
//            default:
//                return "text/plain";
//        }
//    }

}