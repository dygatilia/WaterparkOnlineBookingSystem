package DAO;

import MODEL.Order;
import java.sql.SQLException;

public interface OrderDAO {
    void addOrderStatus(Order order) throws SQLException;
    Order retrieveOrderStatusByBookingID(int bookingid) throws SQLException;
    Order retrieveOrderStatusByOrderID(int orderid) throws SQLException;
    void update(Order order) throws SQLException;
}
