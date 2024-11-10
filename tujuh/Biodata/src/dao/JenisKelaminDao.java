package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import model.JenisKelamin;

public class JenisKelaminDao {
    public int insert(JenisKelamin jenisKelamin) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("insert into jenis_kelamin (id, nama_jenis) values (?, ?)");
            statement.setString(1, jenisKelamin.getId());
            statement.setString(2, jenisKelamin.getNamaJenis());

            result = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(JenisKelamin jenisKelamin) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("update jenis_kelamin set nama_jenis = ? where id = ?");
            statement.setString(1, jenisKelamin.getNamaJenis());
            statement.setString(2, jenisKelamin.getId());

            result = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(JenisKelamin jenisKelamin) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from jenis_kelamin where id = ?");
            statement.setString(1, jenisKelamin.getId());

            result = statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<JenisKelamin> findAll(){
        List<JenisKelamin> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();) {
            try(ResultSet resultSet = statement.executeQuery("select * from jenis_kelamin");) {
                //Retrieving the data
                while(resultSet.next()) {
                    JenisKelamin jenisKelamin = new JenisKelamin();
                    jenisKelamin.setId(resultSet.getString("id"));
                    jenisKelamin.setNamaJenis(resultSet.getString("nama_jenis"));
                    list.add(jenisKelamin);
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
