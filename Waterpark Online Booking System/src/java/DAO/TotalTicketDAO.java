package DAO;

import MODEL.TotalTicket;
import java.sql.SQLException;

public interface TotalTicketDAO {
    void addTotalTicket(TotalTicket totalticket) throws SQLException;
    TotalTicket retrieveTotalTicket() throws SQLException;
    /*TotalTicket retrieveTotalTicketByBookingID(int bookingid) throws SQLException;
    TotalTicket retrieveTotalTicketByBookingStaffID(int bookingstaffid) throws SQLException;*/
    void update(TotalTicket totalticket) throws SQLException;
}
