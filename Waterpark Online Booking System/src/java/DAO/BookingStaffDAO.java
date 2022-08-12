package DAO;

import MODEL.BookingStaff;
import java.sql.SQLException;
import java.util.List;

public interface BookingStaffDAO {
    void addBookingStaff(BookingStaff bookingstaff) throws SQLException;
    BookingStaff retrieveBookingStaffByStaffId(int staffid) throws SQLException;
    BookingStaff retrieveBookingStaffByBookingStaffId(int bookingstaffid) throws SQLException;
    List<BookingStaff> retrieveAllBookingStaffByStaffId (int staffid) throws SQLException;
    List<BookingStaff> retrieveAllBookingStaff () throws SQLException;
    void delete(BookingStaff bookingstaff);
}
