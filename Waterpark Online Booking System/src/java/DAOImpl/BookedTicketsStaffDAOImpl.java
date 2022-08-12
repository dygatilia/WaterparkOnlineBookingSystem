package DAOImpl;

import DAO.BookedTicketsStaffDAO;
import DATABASE.DBConnection;
import MODEL.BookedTicketsStaff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookedTicketsStaffDAOImpl implements BookedTicketsStaffDAO {
    private final Connection con;
    
    public BookedTicketsStaffDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addBookedTicketsStaff(BookedTicketsStaff bookedticketsstaff) throws SQLException {
        try {
            String mySQLQuery = "insert into bookedtickets_staff(ticket_id, bookstaffid, quantity) values(?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, bookedticketsstaff.getTicketid());
            myPS.setInt(2, bookedticketsstaff.getBookid());
            myPS.setInt(3, bookedticketsstaff.getQuantity());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }

    @Override
    public BookedTicketsStaff retrieveBookedTicketsStaffByBookedTicketsStaffId(int bookedticketsstaffid) throws SQLException {
        BookedTicketsStaff bookedticketsstaff = new BookedTicketsStaff();
        
        try {
            String sqlQuery = "select * from bookedtickets_staff where bookedticstaffid =" +bookedticketsstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedticketsstaff.setBookedticid(bookedticketsstaffid);
                bookedticketsstaff.setTicketid(rs.getInt(2));
                bookedticketsstaff.setBookid(rs.getInt(3));
                bookedticketsstaff.setQuantity(rs.getInt(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedticketsstaff;
    }
    
    @Override
    public BookedTicketsStaff retrieveBookedTicketsStaffByBookingStaffId(int bookingstaffid) throws SQLException {
        BookedTicketsStaff bookedticketsstaff = new BookedTicketsStaff();
        
        try {
            String sqlQuery = "select * from bookedtickets_staff where bookstaffid =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedticketsstaff.setBookedticid(rs.getInt(1));
                bookedticketsstaff.setTicketid(rs.getInt(2));
                bookedticketsstaff.setQuantity(rs.getInt(4));
                bookedticketsstaff.setBookid(bookingstaffid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedticketsstaff;
    }
    
    @Override
    public BookedTicketsStaff retrieveQuantityByTicketIDnBookingStaffId(int ticid, int bookingstaffid) throws SQLException {
        BookedTicketsStaff bookedticketsstaff = new BookedTicketsStaff();
        
        try {
            String sqlQuery = "select * from bookedtickets_staff where ticket_id =" + ticid + " and bookstaffid =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedticketsstaff.setQuantity(rs.getInt(4));
                bookedticketsstaff.setTicketid(ticid);
                bookedticketsstaff.setBookid(bookingstaffid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedticketsstaff;
    }
    
    @Override
    public List<BookedTicketsStaff> retrieveAllBookedTicketsStaffByBookingStaffId(int bookingstaffid) throws SQLException {
        List<BookedTicketsStaff> bookedticketsstafflist = new ArrayList<>();
        
        BookedTicketsStaff bookedticketsstaff;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from bookedtickets_staff where bookstaffid =" +bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                bookedticketsstaff = new BookedTicketsStaff();
                
                bookedticketsstaff.setBookedticid(rs.getInt(1));
                bookedticketsstaff.setTicketid(rs.getInt(2));
                bookedticketsstaff.setQuantity(rs.getInt(4));
                bookedticketsstaff.setBookid(bookingstaffid);
                
                bookedticketsstafflist.add(bookedticketsstaff);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookedticketsstafflist;
    }
    
    @Override
    public void delete(BookedTicketsStaff bookedticketsstaff) {
        
        String myQ = "delete from bookedtickets_staff where bookstaffid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, bookedticketsstaff.getBookid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookedTicketsStaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
