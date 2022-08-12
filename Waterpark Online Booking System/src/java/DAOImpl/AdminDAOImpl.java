package DAOImpl;

import DAO.AdminDAO;
import DATABASE.DBConnection;
import MODEL.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAOImpl implements AdminDAO {

    private final Connection con;

    public AdminDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @Override
    public void save(Admin admin) throws SQLException {
        try {
            String mySQLQuery = "insert into admin(ademail, adpassword) values(?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, admin.getAdEmail());
            myPS.setString(2, admin.getAdPassword());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }

    @Override
    public Admin authentication(String email, String password) throws SQLException {
        ResultSet rs = null;
        Admin admin = null;

        try {
            String mySQLQuery = "select * from admin where ademail=? and adpassword=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                admin = new Admin();

                admin.setAdEmail(email);
                admin.setAdPassword(password);
                admin.setAdminID(rs.getInt(1));
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

        return admin;
    }

    @Override
    public List<Admin> retrieveAll() throws SQLException {
        List<Admin> adminlist = new ArrayList<>();

        Admin admin;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from admin";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                admin = new Admin();

                admin.setAdEmail(rs.getString(1));
                admin.setAdPassword(rs.getString(2));

                adminlist.add(admin);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return adminlist;
    }

    @Override
    public void update(Admin admin) throws SQLException {

        try {
            String myQ = "UPDATE admin SET ademail=?, adpassword=? WHERE adminid=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, admin.getAdEmail());
            myPS.setString(2, admin.getAdPassword());
            myPS.setInt(3, admin.getAdminID());

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
    public void delete(Admin admin) throws SQLException {

        String myQ = "DELETE FROM admin WHERE adminid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, admin.getAdminID());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Admin getOne(int id) throws SQLException {

        Admin admin = null;

        try {
            String mySQLQuery = "select * from admin where adminid=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                admin = new Admin();

                admin.setAdminID(id);
                admin.setAdEmail(rs.getString(2));
                admin.setAdPassword(rs.getString(3));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return admin;
    }

    @Override
    public Admin verifyEmail (String email) throws SQLException {
        ResultSet rs = null;
        Admin admin = null;
        
        try {
            String mySQLQuery = "select * from admin where ademail=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);
            
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                admin = new Admin();
                
                admin.setAdEmail(email);
                admin.setAdminID(rs.getInt(1));
                admin.setAdPassword(rs.getString(3));
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
        
        return admin;
    }
}