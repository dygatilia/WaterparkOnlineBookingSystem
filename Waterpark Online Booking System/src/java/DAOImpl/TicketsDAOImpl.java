package DAOImpl;

import DAO.TicketsDAO;
import DATABASE.DBConnection;
import MODEL.Tickets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketsDAOImpl implements TicketsDAO {
    
    private final Connection con;

    public TicketsDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    @Override
    public void save(Tickets tickets) throws SQLException {
        try {
            String mySQLQuery = "insert into ticket(ticketname, price) values(?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, tickets.getTicketName());
            myPS.setDouble(2, tickets.getPrice());
            
            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Tickets> retrieveAll() throws SQLException {
        List<Tickets> ticketslist = new ArrayList<>();

        Tickets tickets;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from ticket";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                tickets = new Tickets();
                
                tickets.setTicketID(rs.getInt(1));
                tickets.setTicketName(rs.getString(2));
                tickets.setPrice(rs.getDouble(3));
                
                ticketslist.add(tickets);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return ticketslist;
    }
    
    @Override
    public Tickets retrieveTicketsByTicketsId(int ticketid) throws SQLException {
        Tickets tickets = new Tickets();
        
        try {
            String sqlQuery = "select * from ticket where ticketid=" +ticketid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                tickets.setTicketID(rs.getInt(1));
                tickets.setTicketName(rs.getString(2));
                tickets.setPrice(rs.getDouble(3));
    
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tickets;
    }
    
    @Override
    public void update(Tickets tickets) throws SQLException {
        try {
            String myQ = "update ticket SET ticketname=?, price=? where ticketid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setString(1, tickets.getTicketName());
            myPS.setDouble(2, tickets.getPrice());
            myPS.setInt(3, tickets.getTicketID());

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
    public void delete(Tickets tickets) throws SQLException {
        
        String myQ = "delete from ticket where ticketid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, tickets.getTicketID());
        
            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TicketsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
