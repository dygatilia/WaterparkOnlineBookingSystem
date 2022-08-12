package DAOImpl;

import DAO.BookedTicketsDAO;
import DATABASE.DBConnection;
import MODEL.BookedTickets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookedTicketsDAOImpl implements BookedTicketsDAO {
    private final Connection con;
    
    public BookedTicketsDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addBookedTickets(BookedTickets bookedtickets) throws SQLException {
        try {
            String mySQLQuery = "insert into bookedtickets(ticketid, bookid, quantity) values(?, ?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, bookedtickets.getTicketid());
            myPS.setInt(2, bookedtickets.getBookid());
            myPS.setInt(3, bookedtickets.getQuantity());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }

    @Override
    public BookedTickets retrieveBookedTicketsByBookedTicketsId(int bookedticketsid) throws SQLException {
        BookedTickets bookedtickets = new BookedTickets();
        
        try {
            String sqlQuery = "select * from bookedtickets where bookedticketsid =" +bookedticketsid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedtickets.setBookedticid(bookedticketsid);
                bookedtickets.setTicketid(rs.getInt(2));
                bookedtickets.setBookid(rs.getInt(3));
                bookedtickets.setQuantity(rs.getInt(4));
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedtickets;
    }
    
    @Override
    public BookedTickets retrieveBookedTicketsByBookingId(int bookingid) throws SQLException {
        BookedTickets bookedtickets = new BookedTickets();
        
        try {
            String sqlQuery = "select * from bookedtickets where bookid =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedtickets.setBookedticid(rs.getInt(1));
                bookedtickets.setTicketid(rs.getInt(2));
                bookedtickets.setQuantity(rs.getInt(4));
                bookedtickets.setBookid(bookingid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedtickets;
    }
    
    @Override
    public BookedTickets retrieveQuantityByTicketIDnBookingId(int ticid, int bookingid) throws SQLException {
        BookedTickets bookedtickets = new BookedTickets();
        
        try {
            String sqlQuery = "select * from bookedtickets where ticketid =" + ticid + " and bookid =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
    
                bookedtickets.setQuantity(rs.getInt(4));
                bookedtickets.setTicketid(ticid);
                bookedtickets.setBookid(bookingid);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookedtickets;
    }
    
    @Override
    public List<BookedTickets> retrieveAllBookedTicketsByBookingId(int bookingid) throws SQLException {
        List<BookedTickets> bookedticketslist = new ArrayList<>();
        
        BookedTickets bookedtickets;
        ResultSet rs = null;
        
        try {
            String sqlQuery = "select * from bookedtickets where bookid =" +bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                bookedtickets = new BookedTickets();
                
                bookedtickets.setBookedticid(rs.getInt(1));
                bookedtickets.setTicketid(rs.getInt(2));
                bookedtickets.setQuantity(rs.getInt(4));
                bookedtickets.setBookid(bookingid);
                
                bookedticketslist.add(bookedtickets);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }
        
        return bookedticketslist;
    }
    
    @Override
    public void delete(BookedTickets bookedtickets) {
        
        String myQ = "delete from bookedtickets where bookid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, bookedtickets.getBookid());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookedTicketsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
