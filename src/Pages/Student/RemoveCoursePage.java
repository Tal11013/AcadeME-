package Pages.Student;

import javax.swing.*;
import java.awt.*;
import University.Student;
import University.University;
import University.Course;

public class RemoveCoursePage extends JFrame {

    private Student student;
    private JTextField courseNameField;

    public RemoveCoursePage(Student student) {
        this.student = student;
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        JLabel registeredCourses = new JLabel("Your Registered Courses:");
        JTextArea coursesLabel = new JTextArea(student.printRegisteredCourses());
        coursesLabel.setEditable(false); // Make the text area read-only
        JLabel courseNameLabel = new JLabel("Enter course name to remove (letters only):");
        courseNameField = new JTextField(20);

        JButton removeButton = new JButton("Remove Course");
        removeButton.addActionListener(e -> {
            try {
                String courseName = courseNameField.getText();
                Course courseToRemove = null;
                for (Course course : student.getCoursesTaken()) {
                    if (courseName.equals(course.getCourseName())) {
                        courseToRemove = course;
                        break;
                    }
                }
                if (courseToRemove == null) {
                    throw new IllegalArgumentException("Course not found in your registered courses");
                }

                student.removeCourse(courseToRemove);
                JOptionPane.showMessageDialog(null, "Course removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(registeredCourses);
        panel.add(coursesLabel);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(removeButton);

        add(panel);
        pack();
        setVisible(true);
    }
}