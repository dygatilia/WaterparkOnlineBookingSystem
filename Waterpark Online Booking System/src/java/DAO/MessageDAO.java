package DAO;

import MODEL.Message;
import java.sql.SQLException;
import java.util.List;

public interface MessageDAO {
    
    void save(Message message) throws SQLException;
    List<Message> retrieveAll() throws SQLException;
    Message retrieveMessageByMessageId(int messageID) throws SQLException;
    void delete(Message message) throws SQLException;
}
