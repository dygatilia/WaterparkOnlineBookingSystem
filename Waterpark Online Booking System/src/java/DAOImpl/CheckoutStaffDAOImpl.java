package DAOImpl;

import DAO.CheckoutStaffDAO;
import DATABASE.DBConnection;
import MODEL.CheckoutStaff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckoutStaffDAOImpl implements CheckoutStaffDAO {
    private final Connection con;
    
    public CheckoutStaffDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void saveCheckoutStaff(CheckoutStaff checkoutstaff) throws SQLException {
        try {
            String mySQLQuery = "insert into checkoutstaff(bookstaff__id, paymenttype, checkoutamount) values(?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, checkoutstaff.getBookid());
            myPS.setString(2, checkoutstaff.getPaymenttype());
            myPS.setDouble(3, checkoutstaff.getCheckoutamount());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public CheckoutStaff retrieveCheckoutStaffByBookingStaffId(int bookstaffid) throws SQLException {
        CheckoutStaff checkoutstaff = new CheckoutStaff();
        
        try {
            String sqlQuery = "select * from checkoutstaff where bookstaff__id =" +bookstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                checkoutstaff.setCheckoutid(rs.getInt(1));
                checkoutstaff.setBookid(bookstaffid);
                checkoutstaff.setPaymenttype(rs.getString(3));
                checkoutstaff.setCheckoutamount(rs.getDouble(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return checkoutstaff;
    }
    
    @Override
    public CheckoutStaff retrieveCheckoutStaffByCheckoutStaffId(int checkoutstaffid) throws SQLException {
        CheckoutStaff checkoutstaff = new CheckoutStaff();
        
        try {
            String sqlQuery = "select * from checkoutstaff where checkoutstaffid =" +checkoutstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                checkoutstaff.setCheckoutid(checkoutstaffid);
                checkoutstaff.setBookid(rs.getInt(2));
                checkoutstaff.setPaymenttype(rs.getString(3));
                checkoutstaff.setCheckoutamount(rs.getDouble(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return checkoutstaff;
    }
    
    @Override
    public void update(CheckoutStaff checkoutstaff) throws SQLException {
        try {
            String myQ = "update checkoutstaff SET bookstaff__id=?, paymenttype=?, checkoutamount=? where checkoutstaffid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setInt(1, checkoutstaff.getBookid());
            myPS.setString(2, checkoutstaff.getPaymenttype());
            myPS.setDouble(3, checkoutstaff.getCheckoutamount());
            myPS.setInt(4, checkoutstaff.getCheckoutid());

            myPS.executeUpdate();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            con.close();
        }
    }
    
    @Override
    public void delete(CheckoutStaff checkoutstaff) throws SQLException {
        
        String myQ = "delete from checkoutstaff where bookstaff__id=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, checkoutstaff.getBookid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutStaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
