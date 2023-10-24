package ServerClient;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyRunnable implements Runnable {
    Socket client_;
    String filename = "";

    public MyRunnable(Socket client) {
        client_ = client;
    }

    @Override
    public void run() {
        Scanner sc = null;
        try {
            sc = new Scanner(client_.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //If there is data available to read
        if (sc.hasNext()) {
            //Read the request and store it in String req
            String req = sc.nextLine();
            System.out.println("req " + req);
            //Splitting the string automatically stores the whole thing in an array and then accessing array[1].
            filename = req.split(" ")[1];
            //For debugging
            System.out.println("filename " + filename);

            //Opening the file
            //Create a File object named file that for requested file.
            //Create a File object named failFile for custom error page.
            File file = new File("/Users/lindsayhaslam/IdeaProjects/Day4/Resources/src/" + filename);
            File failFile = new File("/Users/lindsayhaslam/CS6011/Day4/Resources/src/failFile.html");
            OutputStream outputStream = null;
            try {
                outputStream = client_.getOutputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

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
                System.out.println(filename.length());
                //fileStream.transferTo(outputStream);
                for( int j = 0; j < file.length(); j++ ) {
                    outputStream.write( fileStream.read() );
                    outputStream.flush();
                    // Thread.sleep( 10 ); // Maybe add <- if images are still loading too quickly...
                }
                outputStream.flush();
                outputStream.close();

            } catch (IOException e) {
                FileInputStream failFileStream = null;
                try {
                    failFileStream = new FileInputStream(failFile);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.write("HTTP/2.0 404 OK\n".getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.write("Content-type: text/html\n".getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.write("\n".getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    failFileStream.transferTo(outputStream);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}

