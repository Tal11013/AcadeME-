package Pages.Teacher;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;
import University.Teacher;
import University.Course;
import University.CourseTime;
import University.Test; // Import the Test class
import University.University;
import java.time.format.DateTimeFormatter; // Import DateTimeFormatter
public class CreateCoursePage extends JFrame {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final JTextField courseIdField;
    private final JTextField courseNameField;
    private final JComboBox<String> dayComboBox;
    private final JTextField startHourField;
    private final JTextField endHourField;
    private final JTextField testDateField; // New field for test date input

    public CreateCoursePage(Teacher teacher) {
        setup();
        courseIdField = new JTextField(20);
        courseNameField = new JTextField(20);
        dayComboBox = new JComboBox<>(new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY"});
        startHourField = new JTextField(10);
        endHourField = new JTextField(10);
        testDateField = new JTextField(10); // Test date field with 10 characters for "DD/MM/YYYY"

        JLabel courseIdLabel = new JLabel("Enter course ID (7 digits):");
        JLabel courseNameLabel = new JLabel("Enter course name (letters only):");
        JLabel dayLabel = new JLabel("Select day:");
        JLabel startHourLabel = new JLabel("Enter start hour (HH:MM):");
        JLabel endHourLabel = new JLabel("Enter end hour (HH:MM):");
        JLabel testDateLabel = new JLabel("Enter test date (DD/MM/YYYY):"); // Test date label

        JButton createButton = new JButton("Create Course");
        createButton.addActionListener(e -> {
            try {
                String courseId = courseIdField.getText();
                String courseName = courseNameField.getText();
                String day = Objects.requireNonNull(dayComboBox.getSelectedItem()).toString();
                String startHour = startHourField.getText();
                String endHour = endHourField.getText();
                String testDate = testDateField.getText(); // Get the test date input

                if (!courseId.matches("\\d{7}")) {
                    throw new IllegalArgumentException("Invalid course ID format. It should be 7 digits.");
                }

                if (!courseName.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Invalid course name format. It should contain letters only.");
                }

                if (!startHour.matches("\\d{2}:\\d{2}")) {
                    throw new IllegalArgumentException("Invalid start hour format. It should be in the format: HH:MM.");
                }

                if (!endHour.matches("\\d{2}:\\d{2}")) {
                    throw new IllegalArgumentException("Invalid end hour format. It should be in the format: HH:MM.");
                }

                if (!testDate.matches("(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}")) {
                    throw new IllegalArgumentException("Invalid test date format. It should be in the format: DD/MM/YYYY.");
                }

                // check if start hour is before end hour
                if (Integer.parseInt(startHour.substring(0, 2)) > Integer.parseInt(endHour.substring(0, 2))) {
                    throw new IllegalArgumentException("Invalid start hour. It should be before the end hour.");
                }

                if (!startHour.matches("(0[8-9]|1[0-8]):00")) {
                    throw new IllegalArgumentException("Invalid start hour. It should be between 8:00 and 18:00 and be 'o'clock' (XX:00).");
                }
        
                // Check if end hour is between 8:00 and 18:00 and is "o'clock" (XX:00)
                if (!endHour.matches("(0[8-9]|1[0-8]):00")) {
                    throw new IllegalArgumentException("Invalid end hour. It should be between 8:00 and 18:00 and be 'o'clock' (XX:00).");
                }

                for ( Course existingCourse : University.getCourses()) {
                    if (existingCourse.getCourseId().equals(courseId)) {
                    throw new IllegalArgumentException("Course with ID " + courseId + " already exists.");
                }
                }

                // Check if the test date is before today's date
                LocalDate parsedTestDate2 = LocalDate.parse(testDateField.getText(), dateFormatter);
                if (parsedTestDate2.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("Invalid test date. It should not be before today's date.");
                }

                
                // If the input is valid, call createCourse function
                CourseTime courseTime = new CourseTime(DayOfWeek.valueOf(day), startHour, endHour);
                 Test test = new Test(testDate);
                teacher.createCourse(courseId, courseName, courseTime, test);

                JOptionPane.showMessageDialog(null, "Course created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the window

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2)); // Adjust the layout for the additional row
        panel.add(courseIdLabel);
        panel.add(courseIdField);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(dayLabel);
        panel.add(dayComboBox);
        panel.add(startHourLabel);
        panel.add(startHourField);
        panel.add(endHourLabel);
        panel.add(endHourField);
        panel.add(testDateLabel); // Add the test date label
        panel.add(testDateField); // Add the test date input field
        panel.add(createButton);

        add(panel);
        pack();
        setVisible(true);
    }

    private void setup() {
        this.setTitle("Create Course");
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        // Center window
        this.setLocationRelativeTo(null);
    }

}

