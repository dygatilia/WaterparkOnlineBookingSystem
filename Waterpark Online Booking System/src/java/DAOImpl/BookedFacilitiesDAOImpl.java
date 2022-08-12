package DAOImpl;

import DAO.BookedFacilitiesDAO;
import DATABASE.DBConnection;
import MODEL.BookedFacilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookedFacilitiesDAOImpl implements BookedFacilitiesDAO {
    private final Connection con;
    
    public BookedFacilitiesDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addBookedFacilities(BookedFacilities bookedfacilities) throws SQLException {
        try {
            String mySQLQuery = "insert into bookedfacilities(facilitiesid, book_id, quantity) values(?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, bookedfacilities.getFacilitiesid());
            myPS.setInt(2, bookedfacilities.getBookid());
            myPS.setInt(3, bookedfacilities.getQuantity());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }

    @Override
    public BookedFacilities retrieveBookedFacilitiesByBookedFacilitiesId(int bookedfacilitiesid) throws SQLException {
        BookedFacilities bookedfacilities = new BookedFacilities();
        
        try {
            String sqlQuery = "select * from bookedfacilities where bookedfacid =" +bookedfacilitiesid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilities.setBookedfacid(bookedfacilitiesid);
                bookedfacilities.setFacilitiesid(rs.getInt(2));
                bookedfacilities.setBookid(rs.getInt(3));
                bookedfacilities.setQuantity(rs.getInt(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilities;
    }
    
    @Override
    public BookedFacilities retrieveQuantityByFacilitiesIDnBookingID(int facid, int bookingid) throws SQLException {
        BookedFacilities bookedfacilities = new BookedFacilities();
        
        try {
            String sqlQuery = "select * from bookedfacilities where facilitiesid =" + facid + " and book_id =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilities.setQuantity(rs.getInt(4));
                bookedfacilities.setFacilitiesid(facid);
                bookedfacilities.setBookid(bookingid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilities;
    
    }
    
    @Override
    public BookedFacilities retrieveQuantityByFacilitiesID(int facid) throws SQLException {
        BookedFacilities bookedfacilities = new BookedFacilities();
        
        try {
            String sqlQuery = "select * from bookedfacilities where facilitiesid =" + facid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilities.setQuantity(rs.getInt(4));
                bookedfacilities.setFacilitiesid(facid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilities;
    
    }
    
    @Override
    public BookedFacilities retrieveBookedFacilitiesByBookingId(int bookingid) throws SQLException {
        BookedFacilities bookedfacilities = new BookedFacilities();
        
        try {
            String sqlQuery = "select * from bookedfacilities where book_id =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilities.setBookedfacid(rs.getInt(1));
                bookedfacilities.setFacilitiesid(rs.getInt(2));
                bookedfacilities.setQuantity(rs.getInt(4));
                bookedfacilities.setBookid(bookingid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilities;
    }
    
    @Override
    public List<BookedFacilities> retrieveAllBookedFacilitiesByBookingId(int bookingid) throws SQLException {
        List<BookedFacilities> bookedfacilitieslist = new ArrayList<>();
        
        BookedFacilities bookedfacilities;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from bookedfacilities where book_id =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                bookedfacilities = new BookedFacilities();
                
                bookedfacilities.setBookedfacid(rs.getInt(1));
                bookedfacilities.setFacilitiesid(rs.getInt(2));
                bookedfacilities.setQuantity(rs.getInt(4));
                bookedfacilities.setBookid(bookingid);
                
                bookedfacilitieslist.add(bookedfacilities);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookedfacilitieslist;
    }
    
    @Override
    public void delete(BookedFacilities bookedfacilities) throws SQLException {
        
        String myQ = "delete from bookedfacilities where book_id=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, bookedfacilities.getBookid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookedFacilitiesDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}