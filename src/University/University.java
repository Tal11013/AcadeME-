package University;

// Import necessary packages

import Pages.Student.StudentHomePage;
import Pages.Teacher.TeacherHomePage;
import Pages.login.loginPage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class University {
    private static ArrayList<Teacher> teachers;
    private static ArrayList<Student> students;
    private static ArrayList<Course> courses = new ArrayList<>();

    // Getters for the class variables
    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    // Method to add a new course to the courses list
    public static void addCourse(Course course) {
        courses.add(course);
    }
    // json methods for courses
    public static void saveCoursesToJson() {
        try (FileWriter fileWriter = new FileWriter("src\\coursePath.json", false)) {
            JSONArray coursesFile = new JSONArray();
            for (Course course : courses) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", course.getCourseId());
                jsonObject.put("name", course.getCourseName());
                jsonObject.put("day", course.getCourseTime().getDay().toString());
                jsonObject.put("startHour", course.getCourseTime().getStartHour().toString());
                jsonObject.put("endHour", course.getCourseTime().getEndHour().toString());
                jsonObject.put("teacherId", course.getTeacher().getId());
    
                JSONArray studentIdsArray = new JSONArray();
                for (Student student : course.getStudents()) {
                    studentIdsArray.put(student.getId());
                }
                jsonObject.put("studentIds", studentIdsArray);
    
                coursesFile.put(jsonObject);
            }
            fileWriter.write(coursesFile.toString(4)); // Indent with 4 spaces for better readability
            fileWriter.write("\n"); // Add a new line for the next append
            System.out.println("Courses data saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCoursesFromJson() {
        try (FileReader fileReader = new FileReader("src\\coursePath.json")) {
            StringBuilder fileContent = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                fileContent.append((char) character);
            }
            JSONArray jsonArray = new JSONArray(fileContent.toString());
            courses.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String day = jsonObject.getString("day");
                String startHour = jsonObject.getString("startHour");
                String endHour = jsonObject.getString("endHour");
                String teacherId = jsonObject.getString("teacherId");
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
                CourseTime courseTime = new CourseTime(dayOfWeek, startHour, endHour);
    
                Teacher teacher = findTeacherById(teacherId);
                Course course = new Course(id, name, courseTime, teacher);
    
                JSONArray studentIdsArray = jsonObject.getJSONArray("studentIds");
                for (int j = 0; j < studentIdsArray.length(); j++) {
                    String studentId = studentIdsArray.getString(j);
                    Student student = findStudentById(studentId); // Implement findStudentById method accordingly
                    course.getStudents().add(student);
                }
    
                courses.add(course);
            }
            System.out.println("Courses data loaded from JSON file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveTestsToJson() {
        try (FileWriter fileWriter = new FileWriter("src\\testPath.json", false)) { // Use 'false' to overwrite the file
            JSONArray testsFile = new JSONArray();
            for (Course course : courses) {
                Test test = course.getTest();
                if (test != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("courseId", course.getCourseId());
                    jsonObject.put("date", test.getFormattedDate());

                    ArrayList<String> studentIds = new ArrayList<>();
                    ArrayList<Integer> grades = new ArrayList<>();

                    for (Map.Entry<Student, Integer> entry : test.getAllGrades().entrySet()) {
                        studentIds.add(entry.getKey().getId());
                        grades.add(entry.getValue());
                    }

                    jsonObject.put("studentIds", new JSONArray(studentIds));
                    jsonObject.put("grades", new JSONArray(grades));

                    testsFile.put(jsonObject);
                }
            }
            fileWriter.write(testsFile.toString(4));
            fileWriter.write("\n");
            System.out.println("Tests data saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTestsFromJson() {
        try (FileReader fileReader = new FileReader("src\\testPath.json")) {
            StringBuilder fileContent = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                fileContent.append((char) character);
            }
            JSONArray jsonArray = new JSONArray(fileContent.toString());
    
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String courseId = jsonObject.getString("courseId");
                String dateString = jsonObject.getString("date");
    
                Course course = findCourseById(courseId);
    
                Test test = new Test(dateString);
    
                JSONArray studentIdsArray = jsonObject.getJSONArray("studentIds");
                JSONArray gradesArray = jsonObject.getJSONArray("grades");
    
                Map<Student, Integer> gradesMap = new HashMap<>();
    
                for (int j = 0; j < studentIdsArray.length(); j++) {
                    String studentId = studentIdsArray.getString(j);
                    int grade = gradesArray.getInt(j);
    
                    Student student = findStudentById(studentId);
                    if (student != null) {
                        gradesMap.put(student, grade);
                    } else {
                        System.out.println("Student with ID " + studentId + " not found.");
                    }
                }
    
                test.grades = gradesMap;
                course.setTest(test);
            }
            System.out.println("Tests data loaded from JSON file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null; // If student with the given ID is not found
    }

    // Method to find a teacher by their ID
    public static Teacher findTeacherById(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId)) {
                return teacher;
            }
        }
        throw new IllegalArgumentException("Teacher not found with ID: " + teacherId);
    }

    public static Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        throw new IllegalArgumentException("Course not found with ID: " + courseId);
    }


    // Method to print a list of courses
    public static String printCoursesList() {
        StringBuilder str = new StringBuilder();
        for (Course course : University.getCourses()) {
            str.append("Course ID: ").append(course.getCourseId()).append("\n");
            str.append("Course Name: ").append(course.getCourseName()).append("\n");
            str.append("Course Time: ").append(course.getCourseTime()).append("\n");
            str.append("Teacher: ").append(course.getTeacher().getName()).append("\n");
            str.append("-------------------------" + "\n");
        }
        return str.toString();
    }

    public static void initializeAllStudentsCourses() {
        for (Student student : students) {
            for (Course course : courses) {
                if (course.getStudents().contains(student)) {
                    student.addCourse(course);
                }
            }
        }
    }

    public static void main(String[] args) {


       teachers= loginPage.loadTeacherFromJson();
       students= loginPage.loadStudentFromJson();
       loadCoursesFromJson();
       loadTestsFromJson();
       initializeAllStudentsCourses();
        //run the login page
        new loginPage();
        loginPage.main(args);


        //create teacher with real id and name and email
        Teacher teacher1 = new Teacher("3325774339", "john doe", "john1@gmail.com", "1234");

        //create number of students
        Student student1 = new Student("123456789", "israel israely", "israel@gmail.com", "1234");


        //create a course
        Test test1 = new Test("12/12/2023");
        CourseTime courseTime1 = new CourseTime(DayOfWeek.SUNDAY,"10:00" , "12:00");
        Course course1 = new Course("1234567", "math", courseTime1, teacher1, test1);


        //run the system
        new TeacherHomePage(teacher1);
        new StudentHomePage(student1);







        // Register a shutdown hook to execute saveCoursesToJson() and saveTestsToJson()
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveCoursesToJson();
            saveTestsToJson();
        }));

    }
}
