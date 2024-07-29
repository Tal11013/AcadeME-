package Pages.Teacher;

import javax.swing.*;
import University.Teacher;
import University.Course;

public class TeacherTestsPage extends JFrame {
    private Teacher teacher;
    private JTable testsTable;
    private JScrollPane scrollPane;

    public TeacherTestsPage(Teacher teacher) {
        this.teacher = teacher;
        setup();
        initializeComponents();
        populateTestsTable();
    }

    private void setup() {
        this.setTitle("TeacherTestsPage");
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Center window
        this.setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        this.setTitle("Teacher Tests");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);

        // Create the JTable and add it to a scroll pane
        testsTable = new JTable();
        scrollPane = new JScrollPane(testsTable);
        this.add(scrollPane);
    }

    private void populateTestsTable() {
        // Get the teacher's courses
        java.util.List<Course> courses = teacher.getCoursesTeaching();

        // Prepare the data for the JTable
        Object[][] data = new Object[courses.size()][3];
        String[] columnNames = {"Course Name", "Test Date", "Test Avg"};

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            String courseName = course.getCourseName();
            String testDate = "N/A";
            String testGrade = "N/A";

            if (course.getTest() != null) {
                testDate = course.getTest().getFormattedDate();
                testGrade = String.valueOf(course.getCourseAverage());
            }

            data[i][0] = courseName;
            data[i][1] = testDate;
            data[i][2] = testGrade;
        }

        // Set the data into the table
        testsTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}