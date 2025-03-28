import javax.swing.*;
import java.awt.*;

public class Splashscreen {
    private JWindow window;

    public Splashscreen() {
        window = new JWindow();

        // Splash screen size
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);

        // Create a panel with a background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        // Add an image
        ImageIcon splashIcon = new ImageIcon(getClass().getResource("bolderline.jpeg"));
        JLabel label = new JLabel(splashIcon);
        panel.add(label, BorderLayout.CENTER);

        // Add a loading message
        JLabel loadingLabel = new JLabel("Bolderline School", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(loadingLabel, BorderLayout.SOUTH);

        window.add(panel);
    }

    public void showSplash(int duration) {
        window.setVisible(true);

        // Pause for the specified duration
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        window.setVisible(false);
        window.dispose();
    }

    public static void main(String[] args) {
        // Show splash screen for 3 seconds
        Splashscreen splash = new Splashscreen();
        splash.showSplash(3000);

        // Launch the dashboard
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}

