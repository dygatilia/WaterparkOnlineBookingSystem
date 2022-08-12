package DAO;

import MODEL.BookedTickets;
import java.sql.SQLException;
import java.util.List;

public interface BookedTicketsDAO {
    
    void addBookedTickets(BookedTickets bookedtickets) throws SQLException;
    BookedTickets retrieveBookedTicketsByBookedTicketsId(int bookedticketsid) throws SQLException;
    BookedTickets retrieveQuantityByTicketIDnBookingId(int ticid, int bookingid) throws SQLException;
    List<BookedTickets> retrieveAllBookedTicketsByBookingId(int bookingid) throws SQLException;
    BookedTickets retrieveBookedTicketsByBookingId(int bookingid) throws SQLException;
    void delete(BookedTickets bookedtickets) throws SQLException;
    
}
