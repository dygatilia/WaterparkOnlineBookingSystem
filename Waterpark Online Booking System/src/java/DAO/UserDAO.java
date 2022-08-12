package DAO;

import MODEL.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    
    void save(User user) throws SQLException;
    User authentication(String email, String password) throws SQLException;
    List<User> retrieveAll() throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
    User getOne(int id) throws SQLException;
    User verifyEmail (String email) throws SQLException;
}
