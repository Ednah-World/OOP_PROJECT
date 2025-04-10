import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class CourseEnrollmentChart extends JFrame {

    public CourseEnrollmentChart() {
        setTitle("Course Enrollment Chart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fetch data from the database
        DefaultCategoryDataset dataset = fetchEnrollmentData();
        // Create bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Number of Students per Course",
                "Course Name",
                "Enrollment Count",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Customize chart appearance
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(java.awt.Color.lightGray);

        // Add to panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        getContentPane().add(chartPanel);
    }

    private DefaultCategoryDataset fetchEnrollmentData() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String url = "jdbc:postgresql://localhost:5432/test"; // your DB details
        String user = "postgres";
        String password = "100100";

        String query = "SELECT c.course_name, COUNT(e.student_id) AS student_count " +
                "FROM Enrollments e " +
                "JOIN Courses c ON e.course_id = c.course_id " +
                "GROUP BY c.course_name";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                dataset.addValue(rs.getInt("student_count"), "Students", rs.getString("course_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseEnrollmentChart chart = new CourseEnrollmentChart();
            chart.setVisible(true);
        });
    }
}

