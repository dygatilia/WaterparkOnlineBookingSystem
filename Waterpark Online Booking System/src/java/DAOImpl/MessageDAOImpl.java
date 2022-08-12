package DAOImpl;

import DAO.MessageDAO;
import DATABASE.DBConnection;
import MODEL.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDAOImpl implements MessageDAO {
    
    private final Connection con;

    public MessageDAOImpl() throws SQLException {
        con = DBConnection.getConnection();
    }
    
    @Override
    public void save(Message message) throws SQLException {
        try {
            String mySQLQuery = "insert into contact(fullname, email, subject, description) values(?, ?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, message.getMessageName());
            myPS.setString(2, message.getMessageEmail());
            myPS.setString(3, message.getMessageSubject());
            myPS.setString(4, message.getMessageDescription());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }
    
    @Override
    public List<Message> retrieveAll() throws SQLException {
        List<Message> messagelist = new ArrayList<>();

        Message message;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from contact";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                message = new Message();

                message.setMessageID(rs.getInt(1));
                message.setMessageName(rs.getString(2));
                message.setMessageEmail(rs.getString(3));
                message.setMessageSubject(rs.getString(4));
                message.setMessageDescription(rs.getString(5));

                messagelist.add(message);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return messagelist;
    }
    
    @Override
    public Message retrieveMessageByMessageId(int messageid) throws SQLException {
       Message message = new Message();
        
        try {
            String sqlQuery = "select * from contact where contactid =" +messageid;
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            ResultSet rs = stat.executeQuery(sqlQuery);
            
            while(rs.next()){              
                message.setMessageID(rs.getInt(1));
                message.setMessageName(rs.getString(2));
                message.setMessageEmail(rs.getString(3));
                message.setMessageSubject(rs.getString(4));
                message.setMessageDescription(rs.getString(5));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return message;
    }
    
    @Override
    public void delete(Message message) throws SQLException {

        String myQ = "DELETE FROM contact WHERE contactid=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, message.getMessageID());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
