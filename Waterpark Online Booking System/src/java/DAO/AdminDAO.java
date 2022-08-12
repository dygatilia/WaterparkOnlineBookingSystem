package DAO;

import MODEL.Admin;
import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    
    void save(Admin admin) throws SQLException;
    Admin authentication(String email, String password) throws SQLException;
    List<Admin> retrieveAll() throws SQLException;
    void update(Admin admin) throws SQLException;
    void delete(Admin admin) throws SQLException;
    Admin getOne(int id) throws SQLException;
    Admin verifyEmail (String email) throws SQLException;
}
