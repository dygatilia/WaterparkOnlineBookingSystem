package DAO;

import MODEL.Tickets;
import java.sql.SQLException;
import java.util.List;

public interface TicketsDAO {
    
    void save (Tickets tickets) throws SQLException;
    List<Tickets> retrieveAll() throws SQLException;
    Tickets retrieveTicketsByTicketsId(int ticketid) throws SQLException;
    void update(Tickets tickets) throws SQLException;
    void delete(Tickets tickets) throws SQLException;
    
}
