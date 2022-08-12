package DAO;

import MODEL.CheckoutStaff;
import java.sql.SQLException;

public interface CheckoutStaffDAO {
    void saveCheckoutStaff(CheckoutStaff checkoutstaff) throws SQLException;
    CheckoutStaff retrieveCheckoutStaffByBookingStaffId(int bookstaffid) throws SQLException;
    CheckoutStaff retrieveCheckoutStaffByCheckoutStaffId(int checkoutstaffid) throws SQLException;
    void update(CheckoutStaff checkoutstaff) throws SQLException;
    void delete(CheckoutStaff checkoutstaff) throws SQLException;
}
