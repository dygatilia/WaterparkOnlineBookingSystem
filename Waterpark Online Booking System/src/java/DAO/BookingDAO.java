package DAO;

import MODEL.Booking;
import java.sql.SQLException;
import java.util.List;

public interface BookingDAO {
    
    void addBooking(Booking booking) throws SQLException;
    Booking retrieveBookingByCustomerId(int customerid) throws SQLException;
    Booking retrieveBookingByBookingId(int bookingid) throws SQLException;
    List<Booking> retrieveAllBookingByCustomerId(int customerid) throws SQLException;
    List<Booking> retrieveAllBooking() throws SQLException;
    void delete(Booking booking) throws SQLException;
    
}
