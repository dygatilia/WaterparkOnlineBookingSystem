package DAO;

import MODEL.Staff;
import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {
    
    void save(Staff staff) throws SQLException;
    Staff authentication(String email, String password) throws SQLException;
    List<Staff> retrieveAll() throws SQLException;
    Staff retrieveStaffByStaffId(int staffid) throws SQLException;
    void update(Staff staff) throws SQLException;
    void delete(Staff staff) throws SQLException;
    Staff getOne(int id) throws SQLException;
    Staff verifyEmail (String staff) throws SQLException;
}
