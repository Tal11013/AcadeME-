package University;

import java.time.LocalTime;
import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Course> courses_enrolled;


    public Student(String id, String name, String email, String password) {
        super(id, name, email, password);
        courses_enrolled = new ArrayList<>();
    }
    //courses
    public void addCourse(Course course) {
        courses_enrolled.add(course);
    }

    public ArrayList<Course> getCoursesEnrolled() {
        return courses_enrolled;
    }

    public void removeCourse(Course course) {
        courses_enrolled.remove(course);
        course.getStudents().remove(this);
        // remove student grade from test
        course.getTest().getAllGrades().remove(this);
    }

    public ArrayList<Course> getCoursesTaken() {
        return courses_enrolled;
    }


    public int payment() {
        return courses_enrolled.size()*1500;
    }

    public boolean checkForOverlap() {
        for (int i = 0; i < courses_enrolled.size(); i++) {
            Course course1 = courses_enrolled.get(i);
            for (int j = i + 1; j < courses_enrolled.size(); j++) {
                Course course2 = courses_enrolled.get(j);
                if (isTimeOverlap(course1, course2)) {
                    return true; // There is an overlap
                }
            }
        }
        return false; // No overlap found
    }

    private boolean isTimeOverlap(Course course1, Course course2) {
        // Check if the day of the week is the same
        if (course1.getCourseTime().getDay() == course2.getCourseTime().getDay()) {
            // Check if the time periods overlap
            LocalTime start1 = course1.getCourseTime().getStartHour();
            LocalTime end1 = course1.getCourseTime().getEndHour();
            LocalTime start2 = course2.getCourseTime().getStartHour();
            LocalTime end2 = course2.getCourseTime().getEndHour();

            if (start1.isBefore(end2) && end1.isAfter(start2)) {
                return true; // There is an overlap
            }
        }
        return false; // No overlap found
    }
    public String printRegisteredCourses() {
        String courses = "";
        for (Course course : courses_enrolled) {
            courses += course.getCourseName() + "\n";
        }
        return courses;
    }

    //toString with name
    @Override
    public String toString() {
        return "Student{" +
                "name=" + getName()  + " " +
                printRegisteredCourses() + '}';
    }
    

}


