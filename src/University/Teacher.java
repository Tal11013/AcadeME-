package University;

import java.util.ArrayList;

public class Teacher extends User {
    public Teacher(String id ,String name, String email, String password) {
        super(id, name, email, password);
    }
   
    // Getters
    public ArrayList<Course> getCoursesTeaching() {
        ArrayList<Course> teacherCoursesArr = new ArrayList<>();
        for (Course course : University.getCourses()) {
            if (course.getTeacher() == this) {
                teacherCoursesArr.add(course);
            }
        }
        return teacherCoursesArr;
    }

    //courses
    public void createCourse(String id, String name, CourseTime courseTime,Test test) {
        Course course = new Course(id, name, courseTime, this, test);
        University.addCourse(course);
    }

    //toString only name
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + getName() + '\'' +
                '}';
    }


}