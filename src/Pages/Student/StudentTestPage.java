package Pages.Student;

import javax.swing.*;
import University.Course;
import University.Student;

public class StudentTestPage extends JFrame {
    private Student student;
    private JTable testsTable;
    private JScrollPane scrollPane;

    public StudentTestPage(Student student) {
        this.student = student;
        setup();
        initializeComponents();
        populateTestsTable();
    }

    private void setup() {
        this.setTitle("StudentTestsPage");
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(600, 400);
    }

    private void initializeComponents() {
        // Create the JTable and add it to a scroll pane
        testsTable = new JTable();
        scrollPane = new JScrollPane(testsTable);
        this.add(scrollPane);
    }

    private void populateTestsTable() {
        // Get the teacher's courses
        java.util.List<Course> courses = student.getCoursesTaken();

        // Prepare the data for the JTable
        Object[][] data = new Object[courses.size()][3];
        String[] columnNames = {"Course Name", "Test Date", "Test Grade"};

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

