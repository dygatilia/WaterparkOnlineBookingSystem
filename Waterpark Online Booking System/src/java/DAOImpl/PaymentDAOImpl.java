package DAOImpl;

import DAO.PaymentDAO;
import DATABASE.DBConnection;
import MODEL.Payment;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

public class PaymentDAOImpl implements PaymentDAO {
    
    private final Connection con;

    public PaymentDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    @Override
    public void save(Payment payment) throws SQLException {
        try {
            String mySQLQuery = "insert into payment(checkoutid, paymentfile) values(?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setInt(1, payment.getCheckoutid());
            
            Part filePart = payment.getPaymentfile();
            InputStream inputStream = null;
            
            if (filePart != null){
                try {
                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                } catch (IOException ex) {
                    Logger.getLogger(FeaturesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                myPS.setBlob(2, inputStream);
            }
            
            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Payment> retrieveAll() throws SQLException {
        List<Payment> paymentlist = new ArrayList<>();

        Payment payment;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from payment";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                payment = new Payment();

                payment.setPaymentid(rs.getInt(1));
                payment.setCheckoutid(rs.getInt(2));

                paymentlist.add(payment);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return paymentlist;
    }
    
    @Override
    public Payment retrievePaymentByPaymentId(int paymentid) throws SQLException {
       Payment payment = new Payment();
        
        try {
            String sqlQuery = "select * from payment where paymentid =" +paymentid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){              
                payment.setPaymentid(rs.getInt(1));
                payment.setCheckoutid(rs.getInt(2));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return payment;
    }
    
    @Override
    public Payment retrievePaymentByCheckoutId(int checkoutid) throws SQLException {
       Payment payment = new Payment();
        
        try {
            String sqlQuery = "select * from payment where checkoutid =" +checkoutid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){              
                payment.setPaymentid(rs.getInt(1));
                payment.setCheckoutid(checkoutid);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return payment;
    }
    
    @Override
    public void update(Payment payment) throws SQLException, IOException {
        try {
            String myQ = "update payment SET checkoutid=?, paymentfile=? where paymentid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setInt(1, payment.getCheckoutid());
            
            Part filePart = payment.getPaymentfile();
            InputStream inputStream = null;
            
            inputStream = filePart.getInputStream();
            
            // fetches input stream of the upload file for the blob column
            myPS.setBlob(2, inputStream);
            myPS.setInt(3, payment.getPaymentid());

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
    public void delete(Payment payment) throws SQLException {
        
        String myQ = "delete from payment where paymentid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, payment.getPaymentid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
