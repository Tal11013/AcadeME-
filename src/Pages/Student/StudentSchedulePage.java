package Pages.Student;

import University.Student;
import University.Course;

import javax.swing.*;



public class StudentSchedulePage extends JFrame {
    private Student student;

    public StudentSchedulePage(Student student) {

        setup();

        // frame
        JFrame f;
        // Table
        JTable j;

        // Frame initialization
        f = new JFrame();

        // Frame Title
        f.setTitle("Course Schedule");

        // Data to be displayed in the JTable
        String[][] data = {
                {"8:00-9:00", "", "", "", "", "", "", ""},
                {"9:00-10:00", "", "", "", "", "", "", ""},
                {"10:00-11:00", "", "", "", "", "", "", ""},
                {"11:00-12:00", "", "", "", "", "", "", ""},
                {"12:00-13:00", "", "", "", "", "", "", ""},
                {"13:00-14:00", "", "", "", "", "", "", ""},
                {"14:00-15:00", "", "", "", "", "", "", ""},
                {"15:00-16:00", "", "", "", "", "", "", ""},
                {"16:00-17:00", "", "", "", "", "", "", ""},
                {"17:00-18:00", "", "", "", "", "", "", ""},
                {"18:00-19:00", "", "", "", "", "", "", ""},
                {"19:00-20:00", "", "", "", "", "", "", ""},
        };

        // Column Names (Days)
        String[] columnNames = {"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for(Course course : student.getCoursesTaken()) {
            switch(course.getCourseTime().getDay()) {
                case SUNDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][1] = course.getCourseName();
                    }
                    break;
                case MONDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][2] = course.getCourseName();
                    }
                    break;
                case TUESDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][3] = course.getCourseName();
                    }
                    break;
                case WEDNESDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][4] = course.getCourseName();
                    }
                    break;
                case THURSDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][5] = course.getCourseName();
                    }
                    break;
                case FRIDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][6] = course.getCourseName();

                    }
                    break;
                case SATURDAY:
                    for(int i = course.getCourseTime().getStartHour().getHour() - 8; i < course.getCourseTime().getEndHour().getHour() - 8; i++ ) {
                        data[i][7] = course.getCourseName();
                    }
                    break;
            }
        }
        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 400, 180);


        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(600, 250);
        // Frame Visible = true
        f.setVisible(true);
    }

    private void setup() {
        this.setTitle("StudentSchedulePage");
        ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Center window
        this.setLocationRelativeTo(null);
    }

}