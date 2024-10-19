import java.awt.Point;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.net.*;

public class Server implements Runnable {
    private Blackboard blackboard;
    private Timer timer;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private boolean isSendingData = false;

    public Server(Blackboard blackboard) {
        this.blackboard = blackboard;

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is waiting for client connection...");
            socket = serverSocket.accept();
            System.out.println("Client connected.");
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Server is running...");
    }

    public void startTransmission() {
        if (!isSendingData) {
            System.out.println("Starting data transmission...");
            isSendingData = true;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendClickData();
                }
            }, 0, 1000 ); // removed division by transmission speed
        }
    }

    public void stopTransmission() {
        if (isSendingData && timer != null) {
            System.out.println("Stopping data transmission...");
            timer.cancel();
            isSendingData = false;
        }
    }

    private void sendClickData() {
        List<Point> clicks = blackboard.getClickPositions();
        if (!clicks.isEmpty()) {
            try {
                objectOutputStream.writeObject(clicks);
                objectOutputStream.flush();
                blackboard.clearClicks();  // Clear clicks after sending
                System.out.println("Sent clicks to client.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer() {
        stopTransmission();
        try {
            if (socket != null) {
                objectOutputStream.close();
                socket.close();
            }
            System.out.println("Server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
