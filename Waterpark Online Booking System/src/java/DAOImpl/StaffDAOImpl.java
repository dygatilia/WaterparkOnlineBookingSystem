package DAOImpl;

import DAO.StaffDAO;
import DATABASE.DBConnection;
import MODEL.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDAOImpl implements StaffDAO {

    private final Connection con;

    public StaffDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @Override
    public void save(Staff staff) throws SQLException {
        try {
            String mySQLQuery = "insert into staff(staffname, staffemail, staffpassword) values(?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, staff.getStaffName());
            myPS.setString(2, staff.getStaffEmail());
            myPS.setString(3, staff.getStaffPassword());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }

    @Override
    public Staff authentication(String email, String password) throws SQLException {
        ResultSet rs = null;
        Staff staff = null;

        try {
            String mySQLQuery = "select * from staff where staffemail=? and staffpassword=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                staff = new Staff();

                staff.setStaffEmail(email);
                staff.setStaffPassword(password);
                staff.setStaffID(rs.getInt(1));
                staff.setStaffName(rs.getString(2));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }
            con.close();
        }

        return staff;
    }

    @Override
    public List<Staff> retrieveAll() throws SQLException {
        List<Staff> stafflist = new ArrayList<>();

        Staff staff;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from staff";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                staff = new Staff();

                staff.setStaffID(rs.getInt(1));
                staff.setStaffName(rs.getString(2));
                staff.setStaffEmail(rs.getString(3));
                staff.setStaffPassword(rs.getString(4));

                stafflist.add(staff);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return stafflist;
    }
    
    @Override
    public Staff retrieveStaffByStaffId(int staffid) throws SQLException {
       Staff staff = new Staff();
        
        try {
            String sqlQuery = "select * from staff where staffid =" +staffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){                
                staff.setStaffID(rs.getInt(1));
                staff.setStaffName(rs.getString(2));
                staff.setStaffEmail(rs.getString(3));
                staff.setStaffPassword(rs.getString(4));    
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return staff;
    }
    
    @Override
    public void update(Staff staff) throws SQLException {

        try {
            String myQ = "UPDATE staff SET staffname=?, staffemail=?, staffpassword=? WHERE staffid=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, staff.getStaffName());
            myPS.setString(2, staff.getStaffEmail());
            myPS.setString(3, staff.getStaffPassword());
            myPS.setInt(4, staff.getStaffID());

            myPS.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Staff staff) throws SQLException {

        String myQ = "DELETE FROM staff WHERE staffid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, staff.getStaffID());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Staff getOne(int id) throws SQLException {

        Staff staff = null;

        try {
            String mySQLQuery = "select * from staff where staffid=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                staff = new Staff();

                staff.setStaffID(id);
                staff.setStaffName(rs.getString(2));
                staff.setStaffEmail(rs.getString(3));
                staff.setStaffPassword(rs.getString(4));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return staff;
    }

    @Override
    public Staff verifyEmail (String email) throws SQLException {
        ResultSet rs = null;
        Staff staff = null;
        
        try {
            String mySQLQuery = "select * from staff where staffemail=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);
            
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                staff = new Staff();
                
                staff.setStaffEmail(email);
                staff.setStaffID(rs.getInt(1));
                staff.setStaffName(rs.getString(2));
                staff.setStaffPassword(rs.getString(4));
            }
            con.close();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if(rs != null){
                rs.close();
            }
            con.close();
        }
        
        return staff;
    }
}
