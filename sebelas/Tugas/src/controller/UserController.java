package controller;

import view.UserView;

import javax.swing.*;

import model.User;
import model.UserMapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.ibatis.session.*;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        // Tambahkan listener
        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
        this.view.addUpdateUserListener(new UpdateUserListener());
        this.view.addDeleteUserListener(new DeleteUserListener());

        // Muat data awal
        refreshTable();
    }

    private void refreshTable() {
        try {
            List<User> users = mapper.getAllUsers();
            Object[][] data = users.stream()
                    .map(user -> new Object[]{
                        user.getId(),
                        user.getName(),
                        user.getNrp(),
                        user.getEmail()
                    })
                    .toArray(Object[][]::new);
            view.setUserList(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Failed to refresh table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String nrp = view.getNRPInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !email.isEmpty() && !nrp.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setNrp(nrp);
                user.setEmail(email);
                try {
                    mapper.insertUser(user);
                    session.commit();
                    JOptionPane.showMessageDialog(view, "User added successfully!");
                    refreshTable(); // Langsung refresh tabel
                } catch (Exception ex) {
                    session.rollback();
                    JOptionPane.showMessageDialog(view, "Error adding user: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class UpdateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Please select a user to update!");
                return;
            }

            String name = view.getNameInput();
            String nrp = view.getNRPInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !nrp.isEmpty() && !email.isEmpty()) {
                try {
                    int id = (int) view.getValueAt(selectedRow, 0);
                    User user = new User();
                    user.setId(id);
                    user.setName(name);
                    user.setNrp(nrp);
                    user.setEmail(email);

                    mapper.update(user);
                    session.commit();

                    JOptionPane.showMessageDialog(view, "User updated successfully!");
                    refreshTable();
                } catch (Exception ex) {
                    session.rollback();
                    JOptionPane.showMessageDialog(view, "Error updating user: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields");
            }
        }
    }

    class DeleteUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Please select a user to delete!");
                return;
            }

            try {
                int id = (int) view.getValueAt(selectedRow, 0);
                User user = new User();
                user.setId(id);

                mapper.delete(user);
                session.commit();

                JOptionPane.showMessageDialog(view, "User deleted successfully!");
                refreshTable();
            } catch (Exception ex) {
                session.rollback();
                JOptionPane.showMessageDialog(view, "Error deleting user: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
        }
    }
}
