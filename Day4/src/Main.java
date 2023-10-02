import java.io.*;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        //Create a ServerSocket object named server that listens on port 8080 for incoming client connections.
        ServerSocket server = new ServerSocket(8080);

        //Enter an infinite loop to continuously listen for incoming client connections.
        String filename = "";
        while (true) {
            Socket client = server.accept();
            //Create a Scanner object named sc to read from the input stream of the client socket.
            Scanner sc = new Scanner(client.getInputStream());

            //If there is data available to read
            if (sc.hasNext()) {
                //Read the request and store it in String req
                String req = sc.nextLine();
                //Splitting the string automatically stores the whole thing in an array and then accessing array[1].
                filename = req.split(" ")[1];
                //For debugging
                System.out.println(filename);

                //Opening the file
                //Create a File object named file that for requested file.
                //Create a File object named failFile for custom error page.
                File file = new File("/Users/lindsayhaslam/IdeaProjects/Day4/Resources/src" + filename);
                File failFile = new File("/Users/lindsayhaslam/CS6011/Day4/Resources/src/failFile.html");
                OutputStream outputStream = client.getOutputStream();

                try {
                    String extension = "";
                    //Extract the file extension from filename.
                    int i = filename.lastIndexOf('.');
                    if (i > 0) {
                        extension = filename.substring(i + 1);
                        System.out.println(extension);
                    }
                    //Creating a file stream to read the contents of the file
                    FileInputStream fileStream = new FileInputStream(file);


                    //Determine the content type of the file being served.
                    if (extension.equals("html")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: text/html\n".getBytes());
                    } else if (extension.equals("css")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: text/css\n".getBytes());
                    } else if (extension.equals("jpeg")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: image/jpeg\n".getBytes());
                    } else if (extension.equals("mp3")) {
                        outputStream.write("HTTP/1.1 200 OK\n".getBytes());
                        outputStream.write("Content-type: audio/mpeg\n".getBytes());
                    }
                    outputStream.write("\n".getBytes());
                    fileStream.transferTo(outputStream);
                    outputStream.flush();
                    outputStream.close();

                } catch(FileNotFoundException e){
                    FileInputStream failFileStream = new FileInputStream(failFile);
                    outputStream.write("HTTP/2.0 404 OK\n".getBytes());
                    outputStream.write("Content-type: text/html\n".getBytes());
                    outputStream.write("\n".getBytes());

                    failFileStream.transferTo(outputStream);
                    outputStream.flush();
                    outputStream.close();
                }
            }
        }
    }
}
