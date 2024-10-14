import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.net.*;

public class Server implements Runnable {
    private WorkArea workArea;
    private int transmissionSpeed; // Speed in data/second
    private Timer timer;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;  // Keep the output stream open

    public Server(WorkArea workArea) {
        this.workArea = workArea;
        this.transmissionSpeed = 60; // Default to 60 frames per second

        try {
            ServerSocket serverSocket = new ServerSocket(12345);  // Create server socket on port 12345
            System.out.println("Server is waiting for client connection...");
            socket = serverSocket.accept();  // Wait for client to connect
            System.out.println("Client connected.");

            // Initialize ObjectOutputStream only once
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTransmissionSpeed(int speed) {
        this.transmissionSpeed = speed;
    }

    @Override
    public void run() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendClickData();
            }
        }, 0, 1000 / transmissionSpeed);  // Schedule based on transmission speed
    }

    public void stopServer() {
        if (timer != null) {
            timer.cancel();
        }
        try {
            if (socket != null) {
                objectOutputStream.close();  // Close the stream before closing the socket
                socket.close();  // Close the socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendClickData() {
        List<Point> clicks = workArea.getClickPositions();
        if (!clicks.isEmpty()) {
            try {
                objectOutputStream.writeObject(clicks);  // Send the list of click points
                objectOutputStream.flush();
                workArea.startTracking();  // Clear the click list after sending (resets tracking)
                System.out.println("Sent clicks to client.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
