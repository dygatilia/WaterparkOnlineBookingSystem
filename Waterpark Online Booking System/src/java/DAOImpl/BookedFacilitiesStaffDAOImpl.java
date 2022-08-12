package DAOImpl;

import DAO.BookedFacilitiesStaffDAO;
import DATABASE.DBConnection;
import MODEL.BookedFacilities;
import MODEL.BookedFacilitiesStaff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookedFacilitiesStaffDAOImpl implements BookedFacilitiesStaffDAO {
    private final Connection con;
    
    public BookedFacilitiesStaffDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addBookedFacilitiesStaff(BookedFacilitiesStaff bookedfacilitiesstaff) throws SQLException {
        try {
            String mySQLQuery = "insert into bookedfacilities_staff(facilities_id, bookstaff_id, quantity) values(?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, bookedfacilitiesstaff.getFacilitiesid());
            myPS.setInt(2, bookedfacilitiesstaff.getBookid());
            myPS.setInt(3, bookedfacilitiesstaff.getQuantity());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }

    @Override
    public BookedFacilitiesStaff retrieveBookedFacilitiesByBookedFacilitiesId(int bookedfacilitiesstaffid) throws SQLException {
        BookedFacilitiesStaff bookedfacilitiesstaff = new BookedFacilitiesStaff();
        
        try {
            String sqlQuery = "select * from bookedfacilities_staff where bookedfacstaffid =" +bookedfacilitiesstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilitiesstaff.setBookedfacid(bookedfacilitiesstaffid);
                bookedfacilitiesstaff.setFacilitiesid(rs.getInt(2));
                bookedfacilitiesstaff.setBookid(rs.getInt(3));
                bookedfacilitiesstaff.setQuantity(rs.getInt(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilitiesstaff;
    }
    
    @Override
    public BookedFacilitiesStaff retrieveQuantityByFacilitiesIDnBookingStaffID(int facid, int bookingstaffid) throws SQLException {
        BookedFacilitiesStaff bookedfacilitiesstaff = new BookedFacilitiesStaff();
        
        try {
            String sqlQuery = "select * from bookedfacilities_staff where facilities_id =" + facid + " and bookstaff_id =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilitiesstaff.setQuantity(rs.getInt(4));
                bookedfacilitiesstaff.setFacilitiesid(facid);
                bookedfacilitiesstaff.setBookid(bookingstaffid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilitiesstaff;
    
    }
    
    @Override
    public BookedFacilitiesStaff retrieveBookedFacilitiesStaffByBookingStaffId(int bookingstaffid) throws SQLException {
        BookedFacilitiesStaff bookedfacilitiesstaff = new BookedFacilitiesStaff();
        
        try {
            String sqlQuery = "select * from bookedfacilities_staff where bookstaff_id =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedfacilitiesstaff.setBookedfacid(rs.getInt(1));
                bookedfacilitiesstaff.setFacilitiesid(rs.getInt(2));
                bookedfacilitiesstaff.setQuantity(rs.getInt(4));
                bookedfacilitiesstaff.setBookid(bookingstaffid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedfacilitiesstaff;
    }
    
    @Override
    public List<BookedFacilitiesStaff> retrieveAllBookedFacilitiesStaffByBookingStaffId(int bookingstaffid) throws SQLException {
        List<BookedFacilitiesStaff> bookedfacilitiesstafflist = new ArrayList<>();
        
        BookedFacilitiesStaff bookedfacilitiesstaff;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from bookedfacilities_staff where bookstaff_id =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                bookedfacilitiesstaff = new BookedFacilitiesStaff();
                
                bookedfacilitiesstaff.setBookedfacid(rs.getInt(1));
                bookedfacilitiesstaff.setFacilitiesid(rs.getInt(2));
                bookedfacilitiesstaff.setQuantity(rs.getInt(4));
                bookedfacilitiesstaff.setBookid(bookingstaffid);
                
                bookedfacilitiesstafflist.add(bookedfacilitiesstaff);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookedfacilitiesstafflist;
    }
    
    @Override
    public void delete(BookedFacilitiesStaff bookedfacilitiesstaff) throws SQLException {
        
        String myQ = "delete from bookedfacilities_staff where bookstaff_id=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, bookedfacilitiesstaff.getBookid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookedFacilitiesStaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
