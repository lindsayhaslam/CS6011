import java.io.*;
import java.util.Scanner;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        //Create object that is going to watch and that is the server socket
        ServerSocket server = new ServerSocket(8080);
        String filename = "";
        while (true) {
            Socket client = server.accept();
            Scanner sc = new Scanner(client.getInputStream()); //Use a better name than sc

            if (sc.hasNext()) {
                String req = sc.nextLine();
                //Splitting the string automatically stores the whole thing in an array and then accessing array[1].
                filename = req.split(" ")[1];
                System.out.println(filename);
            }
            //Opening the file
            File file = new File("/Users/lindsayhaslam/IdeaProjects/Day4/Resources/src" + filename);
            String extension = "";

            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i + 1);
                System.out.println(extension);
            }

            //Creating a file stream to read the contents of the file
            FileInputStream fileStream = new FileInputStream(file);

            OutputStream outputStream = client.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            // Check if the requested file does not exist or is not a regular file.
            if (!file.exists() || !file.isFile()) {
                // If the file is not found, create a 404 Not Found response.
                String response = "HTTP/1.1 404 Not Found\n" +
                        // Set the response content type to HTML.
                        "Content-type: text/html\n" +
                        // Add a blank line to separate headers from the response body.
                        "\n" +
                        // HTML response body with a 404 message
                        "<html><body><h1>404 Not Found</h1></body></html>";
                // Convert the response string to bytes and write it to the output stream.
                outputStream.write(response.getBytes());
            }
            // else if the file does exist
            else if (file.exists()) {
                // Set the appropriate Content-type header based on the file extension
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
            }
            // Add a blank line before sending the file contents
            outputStream.write("\n".getBytes());
            // Transfer the file's contents to the output stream
            fileStream.transferTo(outputStream);

            // Flush and close the output stream
            outputStream.flush();
            outputStream.close();
        }
    }
}


//
//            //if the requested file is found, if not found send a message
//            outStream.write("HTTP/1.1 200\n");
//
//            //Send the http header
//            //Send the message to the client
//            //outStream.print("http/1.1 200 Success\n");
//            outStream.write("Content-type: text/html\n");
//            outStream.write("\n");
//            //fileStream.transferTo(os);
//            //Loops over all the content
//            Scanner fc = new Scanner(fileStream);
//            while (fc.hasNext()) {
//                String line = fc.nextLine();
//                outStream.print(line);
//            }
//            outStream.println();
//            outStream.flush();
//            outStream.close();

        ///NNNOOOOOTETTTEEESSSS
        //String path = "/Users/lindsayhaslam/..."
        //FileInputStream filestream = new FileInputStream(path);

        //System.out.println(filestream.read());
        //System.out.println(char(filestream.read()));

        //Scanner scanner = new Scanner(filestream);
        //scanner.useDelimiter("i"); //Whenever I hit "i", it separates to the next line and "i" disappears
        //while (scanner.hasNext()){
        //System.out.println(scanner.next()); //This goes until the n
