package loginSystem;

import javax.swing.*;

import Database.GeneralDatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class LoginPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Username:");
    JLabel userPassLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> loginInfo;

    static final String FILE_PATH = "userinfo.ser";

    LoginPage() {
        loadLoginInfo();

        userIDLabel.setBounds(50, 100, 75, 25);
        userPassLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        registerButton.setBounds(235, 200, 100, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        frame.add(userIDLabel);
        frame.add(userPassLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(registerButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = new String(userPasswordField.getPassword());

            if (userID.equals("admin") && password.equals("admin")) {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Admin login is successful!");
                frame.dispose();
                GeneralDatabase generalDatabase = new GeneralDatabase(password);
                return;
            }

            if (loginInfo.containsKey(userID)) {
                if (loginInfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful!");

                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect password!");
                }
            } else {
                int choice = JOptionPane.showConfirmDialog(frame,
                        "User not found. Do you want to register?",
                        "User not found", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    registerUser();
                }
            }
        }
    }

    private void registerUser() {
        String username = JOptionPane.showInputDialog(frame, "Enter username:");
        String password = JOptionPane.showInputDialog(frame, "Enter password:");
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(frame, "Password is too short! Minimum 6 characters required.");
            } else {
                loginInfo.put(username, password);
                saveLoginInfo(); // Save updated login information
                JOptionPane.showMessageDialog(frame, "Registration successful!");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Username or password cannot be empty!");
        }
    }

    private void saveLoginInfo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(loginInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLoginInfo() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                loginInfo = (HashMap<String, String>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                loginInfo = new HashMap<>();
            }
        } else {
            loginInfo = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
