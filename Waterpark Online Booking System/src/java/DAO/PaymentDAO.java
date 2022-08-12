package DAO;

import MODEL.Payment;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO {
    
    void save(Payment payment) throws SQLException;
    List<Payment> retrieveAll() throws SQLException;
    Payment retrievePaymentByPaymentId(int paymentid) throws SQLException;
    Payment retrievePaymentByCheckoutId(int checkoutid) throws SQLException;
    void update(Payment payment) throws SQLException, IOException;
    void delete(Payment payment) throws SQLException;
    
}
