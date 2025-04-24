import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Splashscreen {
    private final JWindow window;

    public Splashscreen() {
        window = new JWindow();

        // Splash screen size
        window.setSize(700, 700);
        window.setLocationRelativeTo(null);

        // Create a panel with a background color
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        // Add an image
        ImageIcon splashIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("bolderline logo.png")));
        JLabel label = new JLabel(splashIcon);
        panel.add(label, BorderLayout.CENTER);

        JLabel loadingLabel = new JLabel("Loading...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 20));
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

