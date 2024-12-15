package dao;

import java.util.List;
import model.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MemberDao {
    private final SqlSessionFactory sqlSessionFactory;

    public MemberDao(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public int insert(Member member){
        int result;
        try(SqlSession session = sqlSessionFactory.openSession()){
            result = session.insert("mapper.MemberMapper.insert", member);
            session.commit();
        }
        return result;
    }
    public int update(Member member){
        int result;
        try (SqlSession session = sqlSessionFactory.openSession()){
            result = session.update("mapper.MemberMapper.update", member);
            session.commit();
        }
        return result;
    }
    public int delete(Member member) {
        int result = 0;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            result = session.delete("mapper.MemberMapper.delete", member);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error saat delete member: " + e.getMessage());
        }
        return result;
    }
    public List<Member> findAll(){
        List<Member> result;
        try (SqlSession session = sqlSessionFactory.openSession()){
            result = session.selectList("mapper.MemberMapper.findAll");
        }
        return result;
    }
    

}