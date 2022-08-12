package DAOImpl;

import DAO.OrderStaffDAO;
import DATABASE.DBConnection;
import MODEL.OrderStaff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStaffDAOImpl implements OrderStaffDAO {
    private final Connection con;
    
    public OrderStaffDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addOrderStatus(OrderStaff orderstaff) throws SQLException {
        try {
            String mySQLQuery = "insert into orderstaff(bookingstaff_id, orderstatus) values(?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, orderstaff.getBookingStaffID());
            myPS.setString(2, orderstaff.getOrderStaffStatus());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public OrderStaff retrieveOrderStatusByBookingStaffID(int bookingstaffid) throws SQLException {
        OrderStaff orderstaff = new OrderStaff();
        
        try {
            String sqlQuery = "select * from orderstaff where bookingstaff_id=" + bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                orderstaff.setOrderStaffID(rs.getInt(1));
                orderstaff.setOrderStaffStatus(rs.getString(3));
                orderstaff.setBookingStaffID(bookingstaffid);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return orderstaff;
    }
    
    @Override
    public OrderStaff retrieveOrderStatusByOrderID(int orderstaffid) throws SQLException {
        OrderStaff orderstaff = new OrderStaff();
        
        try {
            String sqlQuery = "select * from orderstaff where orderid =" +orderstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                orderstaff.setOrderStaffID(orderstaffid);
                orderstaff.setBookingStaffID(rs.getInt(2));
                orderstaff.setOrderStaffStatus(rs.getString(3));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return orderstaff;
    }
    
    @Override
    public void update(OrderStaff orderstaff) throws SQLException {
        try {
            String myQ = "update orderstaff SET bookingstaff_id=?, orderstatus=? where orderid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setInt(1, orderstaff.getBookingStaffID());
            myPS.setString(2, orderstaff.getOrderStaffStatus());
            myPS.setInt(3, orderstaff.getOrderStaffID());

            myPS.executeUpdate();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            con.close();
        }
    }
}
