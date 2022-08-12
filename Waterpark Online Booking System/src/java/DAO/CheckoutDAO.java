package DAO;

import MODEL.Checkout;
import java.sql.SQLException;

public interface CheckoutDAO {
    void saveCheckout(Checkout checkout) throws SQLException;
    Checkout retrieveCheckoutByBookingId(int bookid) throws SQLException;
    Checkout retrieveCheckoutByCheckoutId(int checkoutid) throws SQLException;
    void update(Checkout checkout) throws SQLException;
    void delete(Checkout checkout) throws SQLException;
}
