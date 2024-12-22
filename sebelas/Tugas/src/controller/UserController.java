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

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
        this.view.addUpdateUserListener(new UpdateUserListener());
        this.view.addDeleteUserListener(new DeleteUserListener());

        // Load initial data
        refreshTable();
    }

    private void refreshTable() {
        List<User> users = mapper.getAllUsers();
        Object[][] data = users.stream()
                .map(user -> new Object[]{user.getId(), user.getName(), user.getNrp(), user.getEmail()})
                .toArray(Object[][]::new);
        view.setUserList(data);
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
                mapper.insertUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view, "User added successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
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

            int id = (int) view.getValueAt(selectedRow, 0);
            String name = view.getNameInput();
            String nrp = view.getNRPInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !nrp.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setNrp(nrp);
                user.setEmail(email);

                mapper.update(user);
                session.commit();
                JOptionPane.showMessageDialog(view, "User updated successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
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

            int id = (int) view.getValueAt(selectedRow, 0);
            mapper.delete(new User() {{
                setId(id);
            }});
            session.commit();
            JOptionPane.showMessageDialog(view, "User deleted successfully!");
            refreshTable();
        }
    }
}
