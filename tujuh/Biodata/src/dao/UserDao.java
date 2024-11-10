package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.User;
import model.JenisKelamin;

public class UserDao {
    public int insert(User user) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("insert into user (id, nama, nomor_hp, jenis_kelamin) values (?, ?, ?, ?)");
            statement.setString(1, user.getId());
            statement.setString(2, user.getNama());
            statement.setString(3, user.getNomorHP());
            statement.setString(4, user.getJenisKelamin().getId());

            result = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(User user) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("update user set nama = ?, nomor_hp = ?, jenis_kelamin = ? where id = ?");
            statement.setString(1, user.getNama());
            statement.setString(2, user.getNomorHP());
            statement.setString(3, user.getJenisKelamin().getId());
            statement.setString(4, user.getId());


            result = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(User user) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from user where id = ?");
            statement.setString(1, user.getId());

            result = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> findAll(){
        List<User> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery("select * from user");) {
                //Retrieving the data
                while(resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setNama(resultSet.getString("nama"));
                    user.setNomorHP(resultSet.getString("nomor_hp"));
                    
                    list.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
