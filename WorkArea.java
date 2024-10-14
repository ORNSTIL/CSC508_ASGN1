import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class WorkArea extends JPanel implements MouseListener {
    private List<Point> clickPositions;
    private boolean tracking;

    public WorkArea() {
        this.clickPositions = new ArrayList<>();
        addMouseListener(this);
    }

    public void startTracking() {
        tracking = true;
        clickPositions.clear();  // Clear when tracking starts
    }

    public void stopTracking() {
        tracking = false;
    }

    public List<Point> getClickPositions() {
        // Return a copy of the list to avoid accidental modification
        return new ArrayList<>(clickPositions);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (tracking) {
            Point clickPosition = e.getPoint();
            clickPositions.add(clickPosition);  // Add new click
            System.out.println("Mouse released at: " + clickPosition);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
