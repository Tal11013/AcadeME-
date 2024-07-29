package Pages.Student;

import javax.swing.*;
import java.awt.*;
import University.Student;


public class StudentCard extends JFrame {

    private Student student;

    public StudentCard(Student student) {
        JLabel nameLabel = new JLabel("Name: " + student.getName());
        JLabel idLabel = new JLabel("ID: " + student.getId());
        JLabel emailLabel = new JLabel("Email: " + student.getEmail());
        this.add(nameLabel);
        this.add(idLabel);
        this.add(emailLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(emailLabel);
        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Course");
        setVisible(true);
    }
}
