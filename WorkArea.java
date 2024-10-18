import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorkArea extends JPanel implements MouseListener {
    private Blackboard blackboard;

    public WorkArea(Blackboard blackboard) {
        this.blackboard = blackboard;
        addMouseListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (blackboard.isTracking()) {
            Point clickPosition = e.getPoint();
            blackboard.addClick(clickPosition);  // Add click to blackboard
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
