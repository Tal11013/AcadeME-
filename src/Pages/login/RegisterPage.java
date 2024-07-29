package Pages.login;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class RegisterPage extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField textField_4;
	private JTextField id;
	private JTextField password;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    // json methods
    public static void saveTeacherToJson(String fullname, String id, String password, String email) {
        try (FileWriter fileWriter = new FileWriter("src\\TeachersData.json", true)) {
            JSONArray TeacherFile = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            
            jsonObject.put("fullname", fullname);
            jsonObject.put("id", id);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            TeacherFile.put(jsonObject);
            
            fileWriter.write(TeacherFile.toString(4)); // Indent with 4 spaces for better readability
            fileWriter.write("\n"); // Add a new line for the next append
            System.out.println("Teacher data appended to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveStudentToJson(String fullname, String id, String password, String email) {
        try (FileWriter fileWriter = new FileWriter("src\\StudentsData.json", true)) {
            JSONArray StudentFile = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            
            jsonObject.put("fullname", fullname);
            jsonObject.put("id", id);
            jsonObject.put("password", password);
            jsonObject.put("email", email);
            StudentFile.put(jsonObject);
            
            fileWriter.write(StudentFile.toString(4)); // Indent with 4 spaces for better readability
            fileWriter.write("\n"); // Add a new line for the next append
            System.out.println("Teacher data appended to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isValidFullName(String fullName) {
        return fullName.matches("^[A-Z][a-z]+\\s[A-Z][a-z]+$");
    }

    private static boolean isValidPassword(String password) {
        // Password should be at least 8 characters with a mix of letters, numbers, and symbols.
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,}$");
    }

    private static boolean isValidEmail(String email) {
        // Simple email validation with a regular expression.
        String emailRegex = "^[a-zA-Z0-9+_.-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static boolean isValidId(String id) {
        // ID should be a string with exactly 9 digits.
        return id.matches("^[0-9]{9}$");
    }
	/**
	 * Create the frame.
	 */
	public RegisterPage() {
		ImageIcon icon = new ImageIcon("src\\Pages\\AcadeIcon.png");
        this.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Register Form");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 234, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Full Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 90, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 123, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Id:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1, 19, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1_1, 0, SpringLayout.EAST, lblNewLabel_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1, 16, SpringLayout.SOUTH, lblNewLabel_1_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1, 0, SpringLayout.WEST, lblNewLabel_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1_2, 21, SpringLayout.SOUTH, lblNewLabel_1_1_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1_2, 144, SpringLayout.WEST, contentPane);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_1_2);
		
		id = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, id, 6, SpringLayout.NORTH, lblNewLabel_1_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, id, 46, SpringLayout.EAST, lblNewLabel_1_2);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, id, -249, SpringLayout.SOUTH, contentPane);
		contentPane.add(id);
		id.setColumns(10);
		
		textField_4 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_4, 6, SpringLayout.NORTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_4, 16, SpringLayout.EAST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_4, 68, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_4, 269, SpringLayout.EAST, lblNewLabel_1);
		textField_4.setColumns(10);
		
		name = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, name, 37, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, name, 16, SpringLayout.EAST, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, name, -19, SpringLayout.NORTH, id);
		sl_contentPane.putConstraint(SpringLayout.EAST, name, -211, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, id, 0, SpringLayout.EAST, name);
		contentPane.add(name);
		name.setColumns(10);
		
		password = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, password, -31, SpringLayout.SOUTH, lblNewLabel_1_1_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, password, 0, SpringLayout.SOUTH, lblNewLabel_1_1_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, password, 258, SpringLayout.WEST, id);
		password.setColumns(10);
		contentPane.add(password);
		
		email = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, email, -31, SpringLayout.SOUTH, lblNewLabel_1_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, email, -253, SpringLayout.EAST, id);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, email, 0, SpringLayout.SOUTH, lblNewLabel_1_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, email, 0, SpringLayout.EAST, id);
		email.setColumns(10);
		contentPane.add(email);
		
		JLabel lblNewLabel_2 = new JLabel("Choose:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 17, SpringLayout.SOUTH, lblNewLabel_1_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 133, SpringLayout.WEST, contentPane);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel_2);
		
		JComboBox studentOrTeacher = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.NORTH, studentOrTeacher, -2, SpringLayout.NORTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, studentOrTeacher, 0, SpringLayout.WEST, id);
		sl_contentPane.putConstraint(SpringLayout.EAST, studentOrTeacher, 163, SpringLayout.EAST, lblNewLabel_2);
		studentOrTeacher.setModel(new DefaultComboBoxModel(new String[] {"Student", "Teacher"}));
		studentOrTeacher.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(studentOrTeacher);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fullname = name.getText();
				String idVal = id.getText();
				String passwordVal = password.getText();
				String emailVal = email.getText();
				String comboBox = studentOrTeacher.getSelectedItem().toString();
				
				boolean validFullName = isValidFullName(fullname);
		        boolean validPassword = isValidPassword(passwordVal);
		        boolean validEmail = isValidEmail(emailVal);
		        boolean validId = isValidId(idVal);

		        if (!validFullName) {
		            showError("Invalid Full Name! Please enter two words with a space between them.");
		        } else if (!validId) {
		            showError("Invalid ID! ID should be a string with exactly 9 numbers.");
		        } else if (!validPassword) {
		            showError("Weak Password! Please use a strong password (at least 8 characters with a mix of letters, numbers, and symbols).");
		        } else if (!validEmail) {
		            showError("Invalid Email! Please enter a valid email address (e.g., example@gmail.com).");
		        } else {
		        	if (comboBox == "Teacher") {
		        		saveTeacherToJson(fullname, idVal, passwordVal, emailVal);
		        	}
		        	else {
		        		saveStudentToJson(fullname, idVal, passwordVal, emailVal);
		        	}
		            showSuccessMessage("Registration Successful!");
					dispose();
					
        			
					
		        }
		        
			}
		}); 
		
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRegister, 0, SpringLayout.WEST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRegister, -48, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnRegister, 215, SpringLayout.WEST, lblNewLabel);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(btnRegister);
	}

}
