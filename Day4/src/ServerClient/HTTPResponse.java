package ServerClient;

import java.io.*;
import java.net.Socket;

public class HTTPResponse {
    private Socket client;
    private String filename;

    public HTTPResponse(Socket client, String filename) {
        this.client = client;
        this.filename = filename;
    }

    public void sendResponse() {
        try {
            OutputStream outputStream = client.getOutputStream();
            File file = new File("/Users/lindsayhaslam/IdeaProjects/Day4/Resources/src/" + filename);
            File failFile = new File("/Users/lindsayhaslam/CS6011/Day4/Resources/src/failFile.html");

            String extension = "";
            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i + 1);
            }

            FileInputStream fileStream = new FileInputStream(file);

            if (file.exists()) {
                sendSuccessfulResponse(outputStream, extension);
                sendFileContents(outputStream, fileStream);
            } else {
                sendErrorResponse(outputStream, failFile);
            }
            for( int j = 0; j < file.length(); j++ ) {
                outputStream.write( fileStream.read() );
                outputStream.flush();
                // Thread.sleep( 10 ); // Maybe add <- if images are still loading too quickly...
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendSuccessfulResponse(OutputStream outputStream, String extension) throws IOException {
        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
        outputStream.write(("Content-type: " + getContentType(extension) + "\n").getBytes());
        outputStream.write("\n".getBytes());

    }

    private void sendErrorResponse(OutputStream outputStream, File failFile) throws IOException {
        try (FileInputStream failFileStream = new FileInputStream(failFile)) {
            outputStream.write("HTTP/2.0 404 OK\n".getBytes());
            outputStream.write("Content-type: text/html\n".getBytes());
            outputStream.write("\n".getBytes());
            failFileStream.transferTo(outputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void sendFileContents(OutputStream outputStream, FileInputStream fileStream) throws IOException {
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = fileStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
    private String getContentType(String extension) {
        switch (extension) {
            case "html":
                return "text/html";
            case "css":
                return "text/css";
            case "jpeg":
                return "image/jpeg";
            case "mp3":
                return "audio/mpeg";
            default:
                return "text/plain";
        }
    }

}