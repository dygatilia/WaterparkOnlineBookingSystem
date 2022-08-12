package DAOImpl;

import DAO.BookingStaffDAO;
import DATABASE.DBConnection;
import MODEL.BookingStaff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingStaffDAOImpl implements BookingStaffDAO {
    private final Connection con;
    
    public BookingStaffDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addBookingStaff(BookingStaff bookingstaff) throws SQLException {
        try {
            String mySQLQuery = "insert into booking_staff(staffid, bookingname, bookingdate, contactnum) values(?, ?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, bookingstaff.getStaffid());
            myPS.setString(2, bookingstaff.getBookingName());
            myPS.setDate(3, bookingstaff.getBookingDate());
            myPS.setString(4, bookingstaff.getContactNum());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public BookingStaff retrieveBookingStaffByStaffId(int staffid) throws SQLException {
        BookingStaff bookingstaff = new BookingStaff();
        
        try {
            String sqlQuery = "select * from booking_staff where staffid=" + staffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                bookingstaff.setBookingStaffID(rs.getInt(1));
                bookingstaff.setBookingName(rs.getString(3));
                bookingstaff.setBookingDate(rs.getDate(4));
                bookingstaff.setContactNum(rs.getString(5));
                bookingstaff.setStaffid(staffid);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return bookingstaff;
    }
    
    @Override
    public BookingStaff retrieveBookingStaffByBookingStaffId(int bookingstaffid) throws SQLException {
        BookingStaff bookingstaff = new BookingStaff();
        
        try {
            String sqlQuery = "select * from booking_staff where bookingstaffid =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookingstaff.setBookingStaffID(bookingstaffid);
                bookingstaff.setStaffid(rs.getInt(2));
                bookingstaff.setBookingName(rs.getString(3));
                bookingstaff.setBookingDate(rs.getDate(4));
                bookingstaff.setContactNum(rs.getString(5));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookingstaff;
    }
    
    @Override
    public List<BookingStaff> retrieveAllBookingStaffByStaffId (int staffid) throws SQLException {
        List<BookingStaff> bookingstafflist = new ArrayList<>();
        
        BookingStaff bookingstaff;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from booking_staff where staffid=" +staffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                bookingstaff = new BookingStaff();
                
                bookingstaff.setBookingStaffID(rs.getInt(1));
                bookingstaff.setBookingName(rs.getString(3));
                bookingstaff.setBookingDate(rs.getDate(4));
                bookingstaff.setContactNum(rs.getString(5));
                bookingstaff.setStaffid(staffid);
                
                bookingstafflist.add(bookingstaff);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookingstafflist;
    }
    
    @Override
    public List<BookingStaff> retrieveAllBookingStaff () throws SQLException {
        List<BookingStaff> bookingstafflist = new ArrayList<>();
        
        BookingStaff bookingstaff;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from booking_staff";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                bookingstaff = new BookingStaff();
                
                bookingstaff.setBookingStaffID(rs.getInt(1));
                bookingstaff.setStaffid(rs.getInt(2));
                bookingstaff.setBookingName(rs.getString(3));
                bookingstaff.setBookingDate(rs.getDate(4));
                bookingstaff.setContactNum(rs.getString(5));
                
                bookingstafflist.add(bookingstaff);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookingstafflist;
    }
    
    @Override
    public void delete(BookingStaff bookingstaff) {
        
        String myQ = "delete from booking_staff where bookingstaffid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, bookingstaff.getBookingStaffID());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookingStaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
