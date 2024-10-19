import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Blackboard {
    private static Blackboard blackboard_instance;
    private List<Point> clickPositions;  // Shared repository of click data
    private int transmissionSpeed;       // Shared transmission speed
    private boolean tracking;            // Shared state for tracking status

    private Blackboard() {
        this.clickPositions = new ArrayList<>();
        this.transmissionSpeed = 60;     // Default speed
        this.tracking = false;           // Initial state
    }

    public static synchronized Blackboard getInstance() {
        if (blackboard_instance == null) {
            blackboard_instance = new Blackboard();
        }
        return blackboard_instance;
    }

    // Add a click position to the blackboard
    public synchronized void addClick(Point click) {
        if (tracking) {
            clickPositions.add(click);
        }
    }

    // Get all click positions (copy to avoid modification)
    public synchronized List<Point> getClickPositions() {
        return new ArrayList<>(clickPositions);
    }

    // Clear click positions
    public synchronized void clearClicks() {
        clickPositions.clear();
    }

    // Transmission speed getter and setter
    public synchronized int getTransmissionSpeed() {
        return transmissionSpeed;
    }

    public synchronized void setTransmissionSpeed(int speed) {
        this.transmissionSpeed = speed;
    }

    // Tracking state control
    public synchronized boolean isTracking() {
        return tracking;
    }

    public synchronized void startTracking() {
        tracking = true;
    }

    public synchronized void stopTracking() {
        tracking = false;
    }
}
