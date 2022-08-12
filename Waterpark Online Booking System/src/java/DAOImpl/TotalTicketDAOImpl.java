package DAOImpl;

import DAO.TotalTicketDAO;
import DATABASE.DBConnection;
import MODEL.TotalTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalTicketDAOImpl implements TotalTicketDAO {
    private final Connection con;
    
    public TotalTicketDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addTotalTicket(TotalTicket totalticket) throws SQLException {
        try {
            String mySQLQuery = "insert into totalticket(ticketdate, totalticketsold) values(?, ?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setDate(1, totalticket.getTicketDate());
            myPS.setInt(2, totalticket.getTicketSold());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public TotalTicket retrieveTotalTicket() throws SQLException {
        TotalTicket totalticket = new TotalTicket();
        
        try {
            String sqlQuery = "select * from totalticket";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                totalticket.setTotalticketID(rs.getInt(1));
                totalticket.setTicketDate(rs.getDate(2));
                totalticket.setTicketSold(rs.getInt(3));
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return totalticket;
    }
    
    /*@Override
    public TotalTicket retrieveTotalTicketByBookingID(int bookingid) throws SQLException {
        TotalTicket totalticket = new TotalTicket();
        
        try {
            String sqlQuery = "select * from totalticket where bookingid=" + bookingid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                totalticket.setTotalticketID(rs.getInt(1));
                totalticket.setBookingstaffID(rs.getInt(3));
                totalticket.setTicketDate(rs.getDate(4));
                totalticket.setTicketSold(rs.getInt(5));
                totalticket.setBookingID(bookingid);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return totalticket;
    }*/
    
    /*@Override
    public TotalTicket retrieveTotalTicketByBookingStaffID(int bookingstaffid) throws SQLException {
        TotalTicket totalticket = new TotalTicket();
        
        try {
            String sqlQuery = "select * from totalticket where bookingstaffid=" + bookingstaffid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                totalticket.setTotalticketID(rs.getInt(1));
                totalticket.setBookingID(rs.getInt(2));
                totalticket.setTicketDate(rs.getDate(4));
                totalticket.setTicketSold(rs.getInt(5));
                totalticket.setBookingstaffID(bookingstaffid);
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return totalticket;
    }*/
    
    @Override
    public void update(TotalTicket totalticket) throws SQLException {
        try {
            String myQ = "update totalticket SET ticketdate=?, totalticketsold=? where totalticketid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setDate(1, totalticket.getTicketDate());
            myPS.setInt(2, totalticket.getTicketSold());
            myPS.setInt(3, totalticket.getTotalticketID());

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
