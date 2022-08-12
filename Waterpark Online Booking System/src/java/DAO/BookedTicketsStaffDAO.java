package DAO;

import MODEL.BookedTicketsStaff;
import java.sql.SQLException;
import java.util.List;

public interface BookedTicketsStaffDAO {
    void addBookedTicketsStaff(BookedTicketsStaff bookedticketsstaff) throws SQLException;
    BookedTicketsStaff retrieveBookedTicketsStaffByBookedTicketsStaffId(int bookedticketsstaffid) throws SQLException;
    BookedTicketsStaff retrieveBookedTicketsStaffByBookingStaffId(int bookingstaffid) throws SQLException;
    BookedTicketsStaff retrieveQuantityByTicketIDnBookingStaffId(int ticid, int bookingstaffid) throws SQLException;
    List<BookedTicketsStaff> retrieveAllBookedTicketsStaffByBookingStaffId(int bookingstaffid) throws SQLException;
    void delete(BookedTicketsStaff bookedticketsstaff);
}
