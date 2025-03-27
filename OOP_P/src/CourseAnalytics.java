import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CourseAnalytics extends JFrame {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";

    public CourseAnalytics() {
        setTitle("Course Analytics");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        add(new ChartPanel(createBarChart()));
        add(new ChartPanel(createPieChart()));

        setVisible(true);
    }

    // 📊 Bar Chart - Students per Course
    private JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT c.course_name, COUNT(e.student_id) AS student_count " +
                     "FROM enrollments e JOIN courses c ON e.course_id = c.course_id " +
                     "GROUP BY c.course_name")) {
            while (rs.next()) {
                dataset.addValue(rs.getInt("student_count"), "Students", rs.getString("course_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Number of Students per Course", "Courses", "Students",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        return chart;
    }

    // 🥧 Pie Chart - Grade Distribution
    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT grade, COUNT(*) AS count FROM enrollments GROUP BY grade")) {
            while (rs.next()) {
                dataset.setValue(rs.getString("grade"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Grade Distribution", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseAnalytics::new);
    }
}

