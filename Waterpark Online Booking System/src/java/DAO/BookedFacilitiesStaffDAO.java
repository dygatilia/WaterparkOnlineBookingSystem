package DAO;

import MODEL.BookedFacilities;
import MODEL.BookedFacilitiesStaff;
import java.sql.SQLException;
import java.util.List;

public interface BookedFacilitiesStaffDAO {
    void addBookedFacilitiesStaff(BookedFacilitiesStaff bookedfacilitiesstaff) throws SQLException;
    BookedFacilitiesStaff retrieveBookedFacilitiesByBookedFacilitiesId(int bookedfacilitiesstaffid) throws SQLException;
    BookedFacilitiesStaff retrieveQuantityByFacilitiesIDnBookingStaffID(int facid, int bookingstaffid) throws SQLException;
    BookedFacilitiesStaff retrieveBookedFacilitiesStaffByBookingStaffId(int bookingstaffid) throws SQLException;
    List<BookedFacilitiesStaff> retrieveAllBookedFacilitiesStaffByBookingStaffId(int bookingstaffid) throws SQLException;
    void delete(BookedFacilitiesStaff bookedfacilitiesstaff) throws SQLException;
}
