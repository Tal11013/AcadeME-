package Pages.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import University.Student;

public class StudentHomePage extends JFrame {

    private Student student;

    public StudentHomePage(Student student) {
        this.student = student;
        initializeComponents();
        setup();
    }

    private void setup() {
        this.setTitle("Student Home Page");
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400); // Increased height to accommodate the "Debt" button
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, 10px spacing

        JButton scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentSchedulePage(student);
            }
        });

        JButton joinCourseButton = new JButton("Join a Course");
        joinCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinCoursePage(student);
            }
        });

        JButton testListButton = new JButton("Test List");
        testListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentTestPage(student);
            }
        });

        JButton studentCardButton = new JButton("Student Card");
        studentCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentCard(student);
            }
        });

        JButton studentDebtButton = new JButton("Debt");
        studentDebtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentDebtPage(student);
            }
        });

        JButton removeCourseButton = new JButton("Remove Course");
        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveCoursePage(student);
            }
        });

        buttonPanel.add(scheduleButton);
        buttonPanel.add(joinCourseButton);
        buttonPanel.add(testListButton);
        buttonPanel.add(studentCardButton);

        // Add the "Debt" button to the button panel
        buttonPanel.add(studentDebtButton);
        buttonPanel.add(removeCourseButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
        this.setVisible(true);
    }
}