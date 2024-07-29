package Pages.Student;

import javax.swing.*;
import java.awt.*;
import University.Student;
import University.University;
import University.Course;

public class JoinCoursePage extends JFrame{

    private Student student;
    private JTextField courseNameField;

    public JoinCoursePage(Student student) {
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        JLabel AvailableCourses = new JLabel("Available Courses:");
        JTextArea CoursesLabel = new JTextArea(University.printCoursesList());
        CoursesLabel.setEditable(false); // Make the text area read-only
        JLabel courseNameLabel = new JLabel("Enter course name (letters only):");
        courseNameField = new JTextField(20);


        JButton createButton = new JButton("Join Course");
        createButton.addActionListener(e -> {
                try {
                	Course addedCourse = null;
                    String courseName = courseNameField.getText();
                    for (Course course : University.getCourses()) {
                        if (courseName.equals(course.getCourseName())) {
                            addedCourse = course;
                            break;
                        }
                    }
                    if (addedCourse == null) {
                        throw new IllegalArgumentException("Course does not exist in this university");
                    }
                    for (int i = 0; i < student.getCoursesTaken().size(); i++) {
                        if (courseName.equals(student.getCoursesTaken().get(i).getCourseName())) {
                            throw new IllegalArgumentException("You are already registered to this course");
                        }
                    }

                    // Check for course time overlap
                    student.addCourse(addedCourse);
                    if (student.checkForOverlap()) {
                        // Remove the course if there is a time overlap
                        student.removeCourse(addedCourse);
                        throw new IllegalArgumentException("Course time overlaps with your other courses");
                    }

                    // If no overlap, add the course and show success message
                    addedCourse.addStudentWithRandomGrade(student);
                    JOptionPane.showMessageDialog(null, "Course added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(AvailableCourses);
        panel.add(CoursesLabel);
        panel.add(courseNameLabel);
        panel.add(courseNameField);
        panel.add(createButton);

        add(panel);
        pack();
        setVisible(true);
    }

}
