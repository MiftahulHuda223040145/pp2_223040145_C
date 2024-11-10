package view.main;

import java.awt.FlowLayout;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.table.*;  
import java.util.*;  
import model.*;  
import view.jeniskelamin.*; 
import view.user.*;
import dao.*;  

public class MainFrame extends JFrame {  
    private JenisKelaminFrame jenisKelaminFrame;  
    private UserFrame userFrame;  
    private JButton buttonJenisKelamin;  
    private JButton buttonUser;  
    private JenisKelaminDao jenisKelaminDao;  
    private UserDao userDao;  

    public MainFrame() {  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setSize(400,500);  
        this.jenisKelaminDao = new JenisKelaminDao();  
        this.userDao = new UserDao();  
        this.jenisKelaminFrame = new JenisKelaminFrame(jenisKelaminDao);  
        this.userFrame = new UserFrame(userDao, jenisKelaminDao);  
        this.setLayout(new FlowLayout());  
        MainButtonActionListener actionListener = new MainButtonActionListener(this);  
        this.buttonJenisKelamin = new JButton("Jenis Kelamin");  
        this.buttonUser = new JButton("User");  
        this.buttonJenisKelamin.addActionListener(actionListener);  
        this.buttonUser.addActionListener(actionListener);  
        this.add(buttonJenisKelamin);  
        this.add(buttonUser);  
    }  

    public JButton getButtonJenisKelamin() {  
        return buttonJenisKelamin;  
    }  

    public JButton getButtonUser() {  
        return buttonUser;  
    }  

    public void showJenisKelaminFrame() {  
        if (jenisKelaminFrame == null) {  
            jenisKelaminFrame = new JenisKelaminFrame(jenisKelaminDao);  
        }  
        jenisKelaminFrame.setVisible(true);  
    }  

    public void showUserFrame() {  
        if (userFrame == null) {  
            userFrame = new UserFrame(userDao, jenisKelaminDao);  
        }  
        userFrame.setVisible(true);  
    }  

    public static void main(String[] args) {  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                MainFrame f = new MainFrame();  
                f.setVisible(true);  
            }  
        });  
    }  
}

//java -cp ".;../libs/mysql-connector-j-9.1.0.jar" view.main.MainFrame
