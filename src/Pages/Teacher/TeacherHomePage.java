package Pages.Teacher;

import javax.swing.*;
import java.awt.*;
import University.Teacher;

public class TeacherHomePage extends JFrame {
    private final Teacher teacher;

    public TeacherHomePage(Teacher teacher) {
        this.teacher = teacher;
        setup();
        initializeComponents();
    }



    private void setup() {
        this.setTitle("Teacher Home Page");
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set window size
        this.setSize(300, 300);
        // Center window
        this.setLocationRelativeTo(null);
        // Disable resize
        this.setResizable(false);
    }

    private void initializeComponents() {
        // Labels and buttons
        JLabel nameLabel = new JLabel("Name: " + teacher.getName());
        JLabel idLabel = new JLabel("ID: " + teacher.getId());
        JLabel emailLabel = new JLabel("Email: " + teacher.getEmail());

        JButton coursesTableButton = new JButton("View Courses");
        coursesTableButton.addActionListener(e -> createCourseTablePage());

        JButton createCourseButton = new JButton("Create Course");
        createCourseButton.addActionListener(e -> createCoursePage());

        JButton viewTestButton = new JButton("View Tests");
        viewTestButton.addActionListener(e -> createTestsPage());

        JButton scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(e -> createSchedulePage()); // Corrected method name

        // Create a vertical BoxLayout for the JFrame
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(boxLayout);

        // Set preferred sizes for the components
        nameLabel.setPreferredSize(new Dimension(300, 30));
        idLabel.setPreferredSize(new Dimension(300, 30));
        emailLabel.setPreferredSize(new Dimension(300, 30));
        createCourseButton.setPreferredSize(new Dimension(300, 30));
        viewTestButton.setPreferredSize(new Dimension(300, 30));
        coursesTableButton.setPreferredSize(new Dimension(300, 30));
        scheduleButton.setPreferredSize(new Dimension(300, 30));
        // Add the components to the JFrame in the order you want them to appear vertically
        add(nameLabel);
        add(idLabel);
        add(emailLabel);
        add(createCourseButton);
        add(viewTestButton);
        add(coursesTableButton);
        add(scheduleButton);

 // Create a single JPanel to hold the components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createVerticalGlue()); // Add flexible space at the top
        contentPanel.add(nameLabel);
        contentPanel.add(idLabel);
        contentPanel.add(emailLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add flexible space between the labels and buttons
        contentPanel.add(createCourseButton);
        contentPanel.add(viewTestButton);
        contentPanel.add(coursesTableButton);
        contentPanel.add(scheduleButton); // Add the schedule button here
        contentPanel.add(Box.createVerticalGlue()); // Add flexible space at the bottom

        // Create a horizontal BoxLayout for the outer container
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.X_AXIS));
        outerPanel.add(Box.createHorizontalGlue()); // Add flexible space to center horizontally
        outerPanel.add(contentPanel);
        outerPanel.add(Box.createHorizontalGlue()); // Add flexible space to center horizontally

        // Set the outer panel as the JFrame's content pane
        setContentPane(outerPanel);

        // Make the JFrame visible
        setVisible(true);
    }

    private void createCoursePage() {
        new CreateCoursePage(teacher).setVisible(true);
    }

    private void createTestsPage() {
        new TeacherTestsPage(teacher).setVisible(true);
    }

    private void createCourseTablePage() {
        new TeacherCoursesPage(teacher).setVisible(true);
    }

    // Corrected method name
    private void createSchedulePage() {
        new TeacherSchedulePage(teacher).setVisible(true);
    }
}
