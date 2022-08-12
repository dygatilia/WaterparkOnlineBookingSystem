package DAOImpl;

import DAO.OrderDAO;
import DATABASE.DBConnection;
import MODEL.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    private final Connection con;
    
    public OrderDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addOrderStatus(Order order) throws SQLException {
        try {
            String mySQLQuery = "insert into orderbooking(booking_id, orderstatus) values(?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, order.getBookingID());
            myPS.setString(2, order.getOrderStatus());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public Order retrieveOrderStatusByBookingID(int bookingid) throws SQLException {
        Order order = new Order();
        
        try {
            String sqlQuery = "select * from orderbooking where booking_id=" + bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                order.setOrderID(rs.getInt(1));
                order.setOrderStatus(rs.getString(3));
                order.setBookingID(bookingid);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return order;
    }
    
    @Override
    public Order retrieveOrderStatusByOrderID(int orderid) throws SQLException {
        Order order = new Order();
        
        try {
            String sqlQuery = "select * from orderbooking where orderid =" +orderid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                order.setOrderID(orderid);
                order.setBookingID(rs.getInt(2));
                order.setOrderStatus(rs.getString(3));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return order;
    }
    
    @Override
    public void update(Order order) throws SQLException {
        try {
            String myQ = "update orderbooking SET booking_id=?, orderstatus=? where orderid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setInt(1, order.getBookingID());
            myPS.setString(2, order.getOrderStatus());
            myPS.setInt(3, order.getOrderID());

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
