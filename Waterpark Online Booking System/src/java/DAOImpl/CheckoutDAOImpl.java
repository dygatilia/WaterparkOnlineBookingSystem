package DAOImpl;

import DAO.CheckoutDAO;
import DATABASE.DBConnection;
import MODEL.Checkout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckoutDAOImpl implements CheckoutDAO {
    private final Connection con;
    
    public CheckoutDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void saveCheckout(Checkout checkout) throws SQLException {
        try {
            String mySQLQuery = "insert into checkout(book__id, paymenttype, checkoutamount) values(?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, checkout.getBookid());
            myPS.setString(2, checkout.getPaymenttype());
            myPS.setDouble(3, checkout.getCheckoutamount());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public Checkout retrieveCheckoutByBookingId(int bookid) throws SQLException {
        Checkout checkout = new Checkout();
        
        try {
            String sqlQuery = "select * from checkout where book__id =" +bookid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                checkout.setCheckoutid(rs.getInt(1));
                checkout.setBookid(bookid);
                checkout.setPaymenttype(rs.getString(3));
                checkout.setCheckoutamount(rs.getDouble(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return checkout;
    }
    
    @Override
    public Checkout retrieveCheckoutByCheckoutId(int checkoutid) throws SQLException {
        Checkout checkout = new Checkout();
        
        try {
            String sqlQuery = "select * from checkout where checkoutid =" +checkoutid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                checkout.setCheckoutid(checkoutid);
                checkout.setBookid(rs.getInt(2));
                checkout.setPaymenttype(rs.getString(3));
                checkout.setCheckoutamount(rs.getDouble(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return checkout;
    }
    
    @Override
    public void update(Checkout checkout) throws SQLException {
        try {
            String myQ = "update checkout SET book__id=?, paymenttype=?, checkoutamount=? where checkoutid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setInt(1, checkout.getBookid());
            myPS.setString(2, checkout.getPaymenttype());
            myPS.setDouble(3, checkout.getCheckoutamount());
            myPS.setInt(4, checkout.getCheckoutid());

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
    public void delete(Checkout checkout) throws SQLException {
        
        String myQ = "delete from checkout where book__id=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, checkout.getBookid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CheckoutDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
