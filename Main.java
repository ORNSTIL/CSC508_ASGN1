import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JMenuItem startMenuItem, stopMenuItem, configureMenuItem;
    private Blackboard blackboard;
    private WorkArea workArea;
    private Server server;
    private Thread serverThread;

    public Main() {
        setTitle("Eye Tracking Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        startMenuItem = new JMenuItem("Start");
        stopMenuItem = new JMenuItem("Stop");
        configureMenuItem = new JMenuItem("Configure");

        menu.add(startMenuItem);
        menu.add(stopMenuItem);
        menu.add(configureMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        startMenuItem.addActionListener(this);
        stopMenuItem.addActionListener(this);
        configureMenuItem.addActionListener(this);

        stopMenuItem.setEnabled(false);  // Disable Stop initially

        blackboard = new Blackboard();
        workArea = new WorkArea(blackboard);
        add(workArea);

        server = new Server(blackboard);
        serverThread = new Thread(server);
        serverThread.start();  // Start the server (keeps it alive)
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startMenuItem) {
            startTracking();
        } else if (e.getSource() == stopMenuItem) {
            stopTracking();
        } else if (e.getSource() == configureMenuItem) {
            configureSettings();
        }
    }

    private void startTracking() {
        startMenuItem.setEnabled(false);
        stopMenuItem.setEnabled(true);
        configureMenuItem.setEnabled(false);

        blackboard.startTracking();  // Update tracking status on blackboard
        server.startTransmission();  // Start data transmission
    }

    private void stopTracking() {
        startMenuItem.setEnabled(true);
        stopMenuItem.setEnabled(false);
        configureMenuItem.setEnabled(true);

        blackboard.stopTracking();  // Stop tracking, but server keeps running
        server.stopTransmission();  // Stop transmission but don't shut down the server
    }

    private void configureSettings() {
        String widthStr = JOptionPane.showInputDialog(this, "Enter width:", "Configure", JOptionPane.QUESTION_MESSAGE);
        String heightStr = JOptionPane.showInputDialog(this, "Enter height:", "Configure", JOptionPane.QUESTION_MESSAGE);
        String speedStr = JOptionPane.showInputDialog(this, "Enter data transmission speed (frames per second):", "Configure", JOptionPane.QUESTION_MESSAGE);

        int width = Integer.parseInt(widthStr);
        int height = Integer.parseInt(heightStr);
        int speed = Integer.parseInt(speedStr);

        setSize(width, height);
        blackboard.setTransmissionSpeed(speed);  // Update the transmission speed in the blackboard
    }
}
