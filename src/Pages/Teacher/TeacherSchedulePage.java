package Pages.Teacher;

import University.Teacher;
import University.Course;

import javax.swing.*;

public class TeacherSchedulePage extends JFrame {
    private Teacher teacher;

    public TeacherSchedulePage(Teacher teacher) {
        this.teacher = teacher;
        initializeComponents();
    }

    private void initializeComponents() {
        // Frame Title
        this.setTitle("Course Schedule");

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
        };

        // Column Names (Days)
        String[] columnNames = {"Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for(Course course : teacher.getCoursesTeaching()) {
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
        JTable j = new JTable(data, columnNames);
        j.setBounds(30, 40, 400, 180);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        this.add(sp);

        // Frame Size
        this.setSize(600, 250);
        // Frame Visible = true
        this.setVisible(true);
    }
}
