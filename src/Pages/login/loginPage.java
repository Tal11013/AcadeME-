package Pages.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import Pages.Student.StudentHomePage;
import Pages.Teacher.TeacherHomePage;
import University.Teacher;
import University.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static University.University.loadCoursesFromJson;

public class loginPage extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField Password;
	private static StudentHomePage studentHomePage;
    private static TeacherHomePage teacherHomePage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
	
    private static void openTeacherHomePage(Teacher user) {
        if (teacherHomePage == null) {
            teacherHomePage = new TeacherHomePage(user);
        }
        teacherHomePage.setVisible(true);
    }

    private static void openStudentHomePage(Student user) {
        if (studentHomePage == null) {
            studentHomePage = new StudentHomePage(user);
        }
        studentHomePage.setVisible(true);
    }
    
	// json Methods
    public static ArrayList loadTeacherFromJson() {
    	try (FileReader fileReader = new FileReader("src\\TeachersData.json")) {
    		StringBuilder fileContent = new StringBuilder();
    		int character;
    		while ((character = fileReader.read()) != -1) {
    			fileContent.append((char) character);
    		}
    		JSONArray jsonArray = new JSONArray(fileContent.toString());
    		ArrayList teachers = new ArrayList<>();
    		for (int i = 0; i < jsonArray.length(); i++) {
    			JSONObject jsonObject = jsonArray.getJSONObject(i);
    			String id = jsonObject.getString("id");
    			String name = jsonObject.getString("fullname");
    			String email = jsonObject.getString("email");
    			String password = jsonObject.getString("password");

    			Teacher teacher = new Teacher(id, name, email, password);
    			teachers.add(teacher);
    		}
    		System.out.println("Teachers data loaded from JSON file.");
        	return teachers;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static ArrayList loadStudentFromJson() {
    	try (FileReader fileReader = new FileReader("src\\StudentsData.json")) {
    		StringBuilder fileContent = new StringBuilder();
    		int character;
    		while ((character = fileReader.read()) != -1) {
    			fileContent.append((char) character);
    		}
    		JSONArray jsonArray = new JSONArray(fileContent.toString());
    		ArrayList students = new ArrayList<>();
    		for (int i = 0; i < jsonArray.length(); i++) {
    			JSONObject jsonObject = jsonArray.getJSONObject(i);
    			String id = jsonObject.getString("id");
    			String name = jsonObject.getString("fullname");
    			String email = jsonObject.getString("email");
    			String password = jsonObject.getString("password");

    			Student student = new Student(id, name, email, password);
    			students.add(student);
    		}
    		System.out.println("Teachers data loaded from JSON file.");
    		return students;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
	/**
	 * Create the frame.
	 */
	public loginPage() {
		ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 192, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 144, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, id, -371, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, id, -171, SpringLayout.EAST, contentPane);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		sl_contentPane.putConstraint(SpringLayout.NORTH, id, 58, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 29, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 262, SpringLayout.WEST, contentPane);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		JLabel lblNewLabel_1 = new JLabel("User Id:");
		sl_contentPane.putConstraint(SpringLayout.WEST, id, 17, SpringLayout.EAST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 130, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel_2);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_1);
		
		Password = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, Password, 6, SpringLayout.NORTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, Password, 23, SpringLayout.EAST, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, Password, -309, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, Password, 0, SpringLayout.EAST, id);
		contentPane.add(Password);
		Password.setColumns(10);
		
		JComboBox studentOrTeacher = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.WEST, studentOrTeacher, 0, SpringLayout.WEST, id);
		sl_contentPane.putConstraint(SpringLayout.EAST, studentOrTeacher, -15, SpringLayout.EAST, lblNewLabel);
		studentOrTeacher.setFont(new Font("Tahoma", Font.PLAIN, 25));
		studentOrTeacher.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher"}));
		contentPane.add(studentOrTeacher);
		
		JLabel lblNewLabel_3 = new JLabel("Choose:");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_3, -34, SpringLayout.WEST, studentOrTeacher);
		sl_contentPane.putConstraint(SpringLayout.NORTH, studentOrTeacher, -3, SpringLayout.NORTH, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 37, SpringLayout.SOUTH, lblNewLabel_2);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_3);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Student> students = new ArrayList<>();
                ArrayList<Teacher> teachers = new ArrayList<>();
                Student userS = null;
                Teacher userT = null;
				String idVal = id.getText();
				String password = Password.getText();
				String comboBox = studentOrTeacher.getSelectedItem().toString();
                boolean isValidLogin = false;
				
				if (comboBox == "Teacher") {
					teachers = loadTeacherFromJson();
					for (Teacher teacher : teachers) {
                        if (teacher.getId().equals(idVal) && teacher.getPassword().equals(password)) {
                            isValidLogin = true;
                            userT = teacher;
                            break;
                        }
                    }
				}
				else {
					students = loadStudentFromJson();
					for (Student student : students) {
                        if (student.getId().equals(idVal) && student.getPassword().equals(password)) {
                            isValidLogin = true;
                            userS = student;
                            break;
                        }
                    }
				}
				
				if (isValidLogin) {
                    showSuccessMessage("Login Successful!");
                    if (comboBox.equals("Teacher")) {
                        openTeacherHomePage(userT);
                    } else {
                        openStudentHomePage(userS);
                    }
                    JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(btnLogin);
                    loginFrame.dispose();
                } else {
                    showError("Invalid ID or Password! Please check your login credentials.");
                }
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnLogin, 46, SpringLayout.SOUTH, studentOrTeacher);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnLogin, 233, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnLogin, -165, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnLogin, -244, SpringLayout.EAST, contentPane);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(btnLogin);
		
		JLabel MovetoRegister = new JLabel("Create An Account");
		MovetoRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterPage().setVisible(true);
				new loginPage().setVisible(false);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, MovetoRegister, 20, SpringLayout.SOUTH, btnLogin);
		sl_contentPane.putConstraint(SpringLayout.WEST, MovetoRegister, 0, SpringLayout.WEST, id);
		sl_contentPane.putConstraint(SpringLayout.EAST, MovetoRegister, -33, SpringLayout.EAST, btnLogin);
		MovetoRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(MovetoRegister);
	}
}
