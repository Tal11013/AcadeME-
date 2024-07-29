package University;
import java.util.Random;

import java.util.ArrayList;
import java.util.Map;
    
public class Course {
    private String id;
    private String name;
    private CourseTime courseTime;
    private Teacher teacher;
    private ArrayList<Student> students;
    private Test test;

    public Course() {
        this.id = "";
        this.name = "";
        this.courseTime = new CourseTime();
        this.teacher = null;
        this.students = null;
        this.test =null;
    }
    //constructor
    public Course(String id, String name, CourseTime courseTime, Teacher teacher, Test test) {
        this.id = id;
        this.name = name;
        this.courseTime = courseTime;
        this.teacher = teacher;
        students = new ArrayList<>();
        this.test = test;

    }

    public Course(String id, String name, CourseTime courseTime, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.courseTime = courseTime;
        this.teacher = teacher;
        students = new ArrayList<>();
    }
    //getters
    public String getCourseName() {
        return name;
    }
    public String getCourseId() {
        return id;
    }
    public CourseTime getCourseTime() {
        return courseTime;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public Test getTest() {
        return test;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }

    //students
    // Method to add a student with a random grade (50-100)
    public void addStudentWithRandomGrade(Student student) {
        Random random = new Random();
        int randomGrade = random.nextInt(51) + 50; // Generate a random number between 50 and 100 (inclusive)
        test.setGrade(student, randomGrade);
        students.add(student);
    }
    public void removeStudent(Student student) {
        students.remove(student);
    }

    //grades
    public double getCourseAverage() {
    Map<Student, Integer> grades = test.getAllGrades();
    double sum = 0;
    for (Integer grade : grades.values()) {
        sum += grade;
    }
    return sum / grades.size();
    }


    public double getStandardDeviation() {
        Map<Student, Integer> grades = test.getAllGrades();
        double average = getCourseAverage();
        double sum = 0;
        for (Integer grade : grades.values()) {
            sum += Math.pow(grade - average, 2);
        }
        return Math.sqrt(sum / grades.size());
    }

    public int[] getCourseDistribution() {
        int[] distribution = new int[5];
        for (Student student : students) {
            if (test.getGrade(student) == null) {
                continue;
            }
            int grade = test.getGrade(student);
            if (grade >= 0 && grade <= 60) {
                distribution[0]++;
            } else if (grade > 60 && grade <= 70) {
                distribution[1]++;
            } else if (grade > 70 && grade <= 80) {
                distribution[2]++;
            } else if (grade > 80 && grade <= 90) {
                distribution[3]++;
            } else if (grade > 90 && grade <= 100) {
                distribution[4]++;
            }
        }

        return distribution;
    }

    //test
    public void setTest(Test test) {
        if (this.test != null) {
            throw new IllegalArgumentException("Test already exists");
        }
        this.test = test;
    }

    //toString
    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", courseTime=" + courseTime +
                ", teacher=" + teacher +
                ", students=" + studentsToString() +
                ", test=" + test +
                '}';
    }

    public String studentsToString() {
        String studentsString = "";
        for (Student student : students) {
            studentsString += student.getName() + ", ";
        }
        return studentsString;
    }
}