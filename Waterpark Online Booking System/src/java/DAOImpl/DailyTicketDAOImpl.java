package DAOImpl;

import DAO.DailyTicketDAO;
import DATABASE.DBConnection;
import MODEL.DailyTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyTicketDAOImpl implements DailyTicketDAO {
    private final Connection con;
    
    public DailyTicketDAOImpl() throws SQLException{
        con = DBConnection.getConnection();
    }

    @Override
    public void addDailyTicket(DailyTicket dailyticket) throws SQLException {
        try {
            String mySQLQuery = "insert into dailyticket(dailyticketmax) values(?)";
            
            PreparedStatement myPS = con.prepareStatement(mySQLQuery);
            
            myPS.setInt(1, dailyticket.getDailyticketmax());
            
            myPS.executeUpdate();
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    
    @Override
    public DailyTicket retrieveDailyTicket() throws SQLException {
        DailyTicket dailyticket = new DailyTicket();
        
        try {
            String sqlQuery = "select * from dailyticket";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){
                
                dailyticket.setDailyticketid(rs.getInt(1));
                dailyticket.setDailyticketmax(rs.getInt(2));
            }
            
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        } 
        return dailyticket;
    }
    
    @Override
    public void update(DailyTicket dailyticket) throws SQLException {
        try {
            String myQ = "update dailyticket SET dailyticketmax=? where dailyticketid=?";
     
            PreparedStatement myPS = con.prepareStatement(myQ);
            
            myPS.setInt(1, dailyticket.getDailyticketmax());
            myPS.setInt(2, dailyticket.getDailyticketid());

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
