package University;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    private Date date;
    public Map<Student, Integer> grades;


    public Test(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error: Invalid date format. Please use the format DD/MM/YYYY.");
            this.date = null;
        }
        grades = new HashMap<>();
    }

    

    public Date getDate() {
        return date;
    }

    // Getter and Setter methods for grades
    public void setGrade(Student student, int grade) {
        grades.put(student, grade);
    }

    public Integer getGrade(Student student) {
        return grades.get(student);
    }

    public Map<Student, Integer> getAllGrades() {
        return grades;
    }

    // Function to get the formatted date as a string
    public String getFormattedDate() {
        if (this.date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(this.date);
        } else {
            return "Invalid Date";
        }
    }


    //toString method
    @Override
    public String toString() {
        return "Test Date: " + getFormattedDate();
    }
}
