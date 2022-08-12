package DAO;

import MODEL.BookedFacilities;
import java.sql.SQLException;
import java.util.List;

public interface BookedFacilitiesDAO {
    
    void addBookedFacilities(BookedFacilities bookedfacilities) throws SQLException;
    BookedFacilities retrieveBookedFacilitiesByBookedFacilitiesId(int bookedfacilitiesid) throws SQLException;
    BookedFacilities retrieveQuantityByFacilitiesIDnBookingID(int facid, int bookingid) throws SQLException;
    BookedFacilities retrieveQuantityByFacilitiesID(int facid) throws SQLException;
    List<BookedFacilities> retrieveAllBookedFacilitiesByBookingId(int bookingid) throws SQLException;
    BookedFacilities retrieveBookedFacilitiesByBookingId(int bookingid) throws SQLException;
    void delete(BookedFacilities bookedfacilities) throws SQLException;
    
}
