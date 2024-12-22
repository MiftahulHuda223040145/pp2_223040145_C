package model;

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Insert("INSERT INTO users (name,nrp,email) VALUES (#{name}, #{nrp}, #{email})")
    void insertUser(User user);

    @Update("update users set name = #{name}, nrp = #{nrp}, email = #{email} where id= #{id}")
    public Integer update(User user);


    @Delete("delete from users where id = #{id}")
    public Integer delete(User user);
}