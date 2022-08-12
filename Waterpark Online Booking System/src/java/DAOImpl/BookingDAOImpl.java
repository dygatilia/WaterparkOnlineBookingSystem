package DAOImpl;

import DAO.BookingDAO;
import DATABASE.DBConnection;
import MODEL.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingDAOImpl implements BookingDAO{
    private final Connection con;
    
    public BookingDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addBooking(Booking booking) throws SQLException {
        try {
            String mySQLQuery = "insert into booking(customerid, bookingname, bookingdate, contactnum) values(?, ?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, booking.getCustomerid());
            myPS.setString(2, booking.getBookingName());
            myPS.setDate(3, booking.getBookingDate());
            myPS.setString(4, booking.getContactNum());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public Booking retrieveBookingByCustomerId(int customerid) throws SQLException {
        Booking booking = new Booking();
        
        try {
            String sqlQuery = "select * from booking where customerid=" + customerid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                booking.setBookingID(rs.getInt(1));
                booking.setBookingName(rs.getString(3));
                booking.setBookingDate(rs.getDate(4));
                booking.setContactNum(rs.getString(5));
                booking.setCustomerid(customerid);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return booking;
    }
    
    @Override
    public Booking retrieveBookingByBookingId(int bookingid) throws SQLException {
        Booking booking = new Booking();
        
        try {
            String sqlQuery = "select * from booking where bookingid =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                booking.setBookingID(bookingid);
                booking.setCustomerid(rs.getInt(2));
                booking.setBookingName(rs.getString(3));
                booking.setBookingDate(rs.getDate(4));
                booking.setContactNum(rs.getString(5));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return booking;
    }
    
    @Override
    public List<Booking> retrieveAllBookingByCustomerId (int customerid) throws SQLException {
        List<Booking> bookinglist = new ArrayList<>();
        
        Booking booking;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from booking where customerid =" +customerid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                booking = new Booking();
                
                booking.setBookingID(rs.getInt(1));
                booking.setBookingName(rs.getString(3));
                booking.setBookingDate(rs.getDate(4));
                booking.setContactNum(rs.getString(5));
                booking.setCustomerid(customerid);
                
                bookinglist.add(booking);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookinglist;
    }
    
    @Override
    public List<Booking> retrieveAllBooking() throws SQLException {
        List<Booking> bookinglist = new ArrayList<>();
        
        Booking booking;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from booking";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                booking = new Booking();
                
                booking.setBookingID(rs.getInt(1));
                booking.setCustomerid(rs.getInt(2));
                booking.setBookingName(rs.getString(3));
                booking.setBookingDate(rs.getDate(4));
                booking.setContactNum(rs.getString(5));
                
                bookinglist.add(booking);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookinglist;
    }
    
    @Override
    public void delete(Booking booking) {
        
        String myQ = "delete from booking where bookingid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, booking.getBookingID());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
