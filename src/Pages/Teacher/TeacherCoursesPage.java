package Pages.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import University.Teacher;
import University.Course;

public class TeacherCoursesPage extends JFrame {
    private Teacher teacher;
    private JPanel gridPanel; // Use JPanel to create the grid layout

    public TeacherCoursesPage(Teacher teacher) {
        this.teacher = teacher;
        initializeComponents();
        populateCoursesGrid();
    }

    private void initializeComponents() {
        this.setTitle("Teacher Courses");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 400);

        // Create the JPanel to hold the grid
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Use GridLayout to create a vertical grid with spacing

        // Add the gridPanel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(gridPanel);

        // Add the scroll pane to the frame
        this.add(scrollPane);
    }

    private void populateCoursesGrid() {
        // Get the teacher's courses
        List<Course> courses = teacher.getCoursesTeaching();

        // Create the grid components for each course
        for (Course course : courses) {
            JPanel coursePanel = new JPanel();
            coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
            coursePanel.setBorder(BorderFactory.createTitledBorder(course.getCourseName()));

            JLabel courseIdLabel = new JLabel("Course ID: " + course.getCourseId());
            JLabel dayLabel = new JLabel("Day: " + course.getCourseTime().getDay().toString());
            JLabel startHourLabel = new JLabel("Start Hour: " + course.getCourseTime().getStartHour().toString());
            JLabel endHourLabel = new JLabel("End Hour: " + course.getCourseTime().getEndHour().toString());

            JButton showDistributionButton = new JButton("Show Distribution");
            showDistributionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showDistributionForCourse(course); // Call the method to show the distribution
                }
            });

            // Add the components to the course panel
            coursePanel.add(courseIdLabel);
            coursePanel.add(dayLabel);
            coursePanel.add(startHourLabel);
            coursePanel.add(endHourLabel);
            coursePanel.add(showDistributionButton);

            // Add the course panel to the grPanel
            gridPanel.add(coursePanel);
        }
    }

    private void showDistributionForCourse(Course course) {
        int[] distribution = course.getCourseDistribution();
        double avg = course.getCourseAverage();
        double standardDeviation = course.getStandardDeviation();

        // Create a dialog box to display the distribution graph
        JFrame graphFrame = new JFrame("Distribution Graph for " + course.getCourseName());
        graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        graphFrame.setSize(400, 300);
        CourseDistributionGraph graph = new CourseDistributionGraph(distribution,avg,standardDeviation);
        graphFrame.add(graph);
        graphFrame.setVisible(true);
    }
}
