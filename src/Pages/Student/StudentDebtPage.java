package Pages.Student;

import javax.swing.*;
import java.awt.*;
import University.Student;

public class StudentDebtPage extends JFrame {

    private Student student;

    public StudentDebtPage(Student student) {
        JLabel debtLabel = new JLabel("Debt: " + student.payment());
        this.add(debtLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(debtLabel);
        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create Course");
        setVisible(true);
    }
}
