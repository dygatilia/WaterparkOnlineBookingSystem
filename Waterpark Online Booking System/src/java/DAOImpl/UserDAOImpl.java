package DAOImpl;

import DAO.UserDAO;
import DATABASE.DBConnection;
import MODEL.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {

    private final Connection con;

    public UserDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }

    @Override
    public void save(User user) throws SQLException {
        try {
            String mySQLQuery = "insert into customer(name, email, password) values(?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, user.getName());
            myPS.setString(2, user.getEmail());
            myPS.setString(3, user.getPassword());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }

    @Override
    public User authentication(String email, String password) throws SQLException {
        ResultSet rs = null;
        User user = null;

        try {
            String mySQLQuery = "select * from customer where email=? and password=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();

                user.setEmail(email);
                user.setPassword(password);
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
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

        return user;
    }

    @Override
    public List<User> retrieveAll() throws SQLException {
        List<User> userlist = new ArrayList<>();

        User user;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from customer";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                user = new User();

                user.setName(rs.getString(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));

                userlist.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return userlist;
    }

    @Override
    public void update(User user) throws SQLException {

        try {
            String myQ = "UPDATE customer SET name=?, email=?, password=? WHERE customerID=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, user.getName());
            myPS.setString(2, user.getEmail());
            myPS.setString(3, user.getPassword());
            myPS.setInt(4, user.getId());

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
    public void delete(User user) throws SQLException {

        String myQ = "DELETE FROM customer WHERE customerID=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, user.getId());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public User getOne(int id) throws SQLException {

        User user = null;

        try {
            String mySQLQuery = "select * from customer where customerID=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();

                user.setId(id);
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return user;
    }

    @Override
    public User verifyEmail (String email) throws SQLException {
        ResultSet rs = null;
        User user = null;
        
        try {
            String mySQLQuery = "select * from customer where email=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);
            
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                user = new User();
                
                user.setEmail(email);
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(4));
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
        
        return user;
    }
}
