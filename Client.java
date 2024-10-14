import java.io.*;
import java.net.*;
import java.awt.Point;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);  // Connect to server on port 12345
            System.out.println("Connected to server.");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                try {
                    // Read the list of click positions from the server
                    List<Point> clicks = (List<Point>) objectInputStream.readObject();
                    System.out.println("Received clicks from server: " + clicks);
                } catch (EOFException eof) {
                    // Server has closed the connection gracefully
                    System.out.println("Server closed connection. Exiting.");
                    break;  // Exit the loop when server closes the connection
                }
            }

            objectInputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
