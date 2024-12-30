package controller;

import model.*;
import javax.swing.*;

import org.apache.ibatis.session.SqlSession;

import controller.UserController.AddRandomListener;
import view.UserPdf;

import view.UserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import java.awt.BorderLayout;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf, SqlSession session){
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
        this.view.getBtnAddRandom().addActionListener(new AddRandomListener());
        this.view.getBtnDelete100().addActionListener(new Delete100Listener());
    }

    class AddUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if(!name.isEmpty() && !email.isEmpty()){
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                mapper.insertUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view,"User added successfully!");
            }else{
                JOptionPane.showMessageDialog(view,"Please fill in all fields");
            }
        }
    }

    class RefreshListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            List<User> users = mapper.getAllUsers();
            String[] userArray = users.stream()
                                .map(u -> u.getName() + " ("+u.getEmail()+")")
                                .toArray(String[]::new);
            view.setUserList(userArray);
        }
    }

    class ExportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            List<User> users = mapper.getAllUsers();
            pdf.exportPdf(users);
        }
    }

    class AddRandomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Adding 100 Users...");
            JProgressBar progressBar = new JProgressBar(0, 100);
            JLabel statusLabel = new JLabel("Adding data...");

            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i < 100; i++) {
                        User user = new User();
                        user.setName("RandomUser" + UUID.randomUUID().toString().substring(0, 6));
                        user.setEmail("random" + i + "@example.com");
                        mapper.insertUser(user);
                        publish(i + 1);
                        Thread.sleep(50); // Simulasi delay
                    }
                    session.commit();
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    int progress = chunks.get(chunks.size() - 1);
                    progressBar.setValue(progress);
                }

                @Override
                protected void done() {
                    frame.dispose();
                    JOptionPane.showMessageDialog(view, "100 Users added successfully!");
                }
            };

            worker.execute();
        }
    }

    class Delete100Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            int count = Math.min(100, users.size());

            JFrame frame = new JFrame("Deleting 100 Users...");
            JProgressBar progressBar = new JProgressBar(0, count);
            JLabel statusLabel = new JLabel("Deleting data...");

            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    for (int i = 0; i < count; i++) {
                        mapper.delete(users.get(i));
                        publish(i + 1);
                        Thread.sleep(50);
                    }
                    session.commit();
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    int progress = chunks.get(chunks.size() - 1);
                    progressBar.setValue(progress);
                }

                @Override
                protected void done() {
                    frame.dispose();
                    JOptionPane.showMessageDialog(view, count + " Users deleted successfully!");
                }
            };

            worker.execute();
        }
    }
    

}
